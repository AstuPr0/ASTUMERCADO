package com.proyecto.pw.MercaMovil.Controller;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.proyecto.pw.MercaMovil.Model.Usuario;
import com.proyecto.pw.MercaMovil.Model.Factura;
import com.proyecto.pw.MercaMovil.Model.FacturaProducto;
import com.proyecto.pw.MercaMovil.Service.CarritoService;
import com.proyecto.pw.MercaMovil.DTO.CarritoDTO;
import com.proyecto.pw.MercaMovil.DTO.CarritoProductoDTO;
import com.proyecto.pw.MercaMovil.DTO.DescuentoDTO;
import com.proyecto.pw.MercaMovil.Service.UsuarioService;
import com.proyecto.pw.MercaMovil.Repository.FacturaRepository;
import com.proyecto.pw.MercaMovil.Repository.FacturaProductoRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MercadoPagoController {

    @Value("${codigo.mercadopago}")
    private String mercadolibretoken;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaProductoRepository facturaProductoRepository;

    @RequestMapping(value = "/api/mercadopago", method = RequestMethod.POST)
    public ResponseEntity<?> createPreference() {
        try {
            MercadoPagoConfig.setAccessToken(mercadolibretoken);

            // Obtener el ID del usuario autenticado
            Long userId = obtenerUserIdDeSesion();

            if (userId == null) {
                return ResponseEntity.badRequest().body(createErrorResponse("Usuario no autenticado"));
            }

            // Obtener el usuario y su carrito
            Usuario usuario = usuarioService.findById(userId);
            if (usuario == null || usuario.getCarrito() == null) {
                return ResponseEntity.badRequest().body(createErrorResponse("Usuario o carrito no encontrado"));
            }

            CarritoDTO carritoDTO = carritoService.getCarrito(usuario.getCarrito().getId());
            List<CarritoProductoDTO> productos = carritoDTO.getProductos();

            List<PreferenceItemRequest> items = new ArrayList<>();
            BigDecimal totalConDescuento = BigDecimal.ZERO;

            for (CarritoProductoDTO producto : productos) {
                // Aplica el descuento al precio unitario si se cumple la cantidad mínima
                BigDecimal precioConDescuento = aplicarDescuento(BigDecimal.valueOf(producto.getProducto().getPrice()), producto.getProducto().getDescuento(), producto.getCantidad());
                totalConDescuento = totalConDescuento.add(precioConDescuento.multiply(BigDecimal.valueOf(producto.getCantidad())));

                PreferenceItemRequest item = PreferenceItemRequest.builder()
                        .title(producto.getProducto().getName())
                        .quantity(producto.getCantidad())
                        .unitPrice(precioConDescuento)
                        .currencyId("COP")
                        .build();
                items.add(item);
            }

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("http://localhost:8080/api/mercadopago/success")
                    .failure("http://localhost:8080/api/mercadopago/failure")
                    .pending("http://localhost:8080/api/mercadopago/pending")
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .build();
            PreferenceClient preferenceClient = new PreferenceClient();
            Preference preference = preferenceClient.create(preferenceRequest);

            return ResponseEntity.ok(preference);
        } catch (MPApiException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(createErrorResponse("Error en la solicitud de pago: " + e.getApiResponse().getContent()));
        } catch (MPException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(createErrorResponse("Error en la solicitud de pago: " + e.getMessage()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(createErrorResponse("Error interno del servidor: " + e.getMessage()));
        }
    }

    @RequestMapping(value = "api/mercadopago/success", method = RequestMethod.GET)
    public ResponseEntity<?> handleSuccess(@RequestParam Map<String, String> params) {
        return handlePayment(params, "success");
    }

    @RequestMapping(value = "api/mercadopago/failure", method = RequestMethod.GET)
    public ResponseEntity<?> handleFailure(@RequestParam Map<String, String> params) {
        return handlePayment(params, "failure");
    }

    @RequestMapping(value = "api/mercadopago/pending", method = RequestMethod.GET)
    public ResponseEntity<?> handlePending(@RequestParam Map<String, String> params) {
        return handlePayment(params, "pending");
    }

        private ResponseEntity<?> handlePayment(Map<String, String> params, String status) {
        try {
            Long userId = obtenerUserIdDeSesion();
    
            if (userId == null) {
                return ResponseEntity.badRequest().body(createErrorResponse("Usuario no autenticado"));
            }
    
            Usuario usuario = usuarioService.findById(userId);
            if (usuario == null) {
                return ResponseEntity.badRequest().body(createErrorResponse("Usuario no encontrado"));
            }
    
            // Obtener los parámetros enviados por MercadoPago
            String paymentId = params.get("payment_id");
            String externalReference = params.get("external_reference");
    
            // Obtener el carrito del usuario
            CarritoDTO carritoDTO = carritoService.getCarrito(usuario.getCarrito().getId());
            List<CarritoProductoDTO> productos = carritoDTO.getProductos();
            BigDecimal totalConDescuento = BigDecimal.ZERO;
    
            for (CarritoProductoDTO producto : productos) {
                BigDecimal precioConDescuento = aplicarDescuento(BigDecimal.valueOf(producto.getProducto().getPrice()), producto.getProducto().getDescuento(), producto.getCantidad());
                totalConDescuento = totalConDescuento.add(precioConDescuento.multiply(BigDecimal.valueOf(producto.getCantidad())));
            }
    
            // Crear y guardar la factura
            Factura factura = new Factura();
            factura.setUsuario(usuario);
            factura.setTotal(totalConDescuento);
            factura.setEstado(status);
            factura.setExternalReference(externalReference);
            factura.setPaymentId(paymentId);
            factura = facturaRepository.save(factura);
    
            for (CarritoProductoDTO producto : productos) {
                FacturaProducto facturaProducto = new FacturaProducto();
                facturaProducto.setFactura(factura);
                facturaProducto.setNombreProducto(producto.getProducto().getName());
                facturaProducto.setCantidad(producto.getCantidad());
                facturaProducto.setPrecio(aplicarDescuento(BigDecimal.valueOf(producto.getProducto().getPrice()), producto.getProducto().getDescuento(), producto.getCantidad()));
                facturaProductoRepository.save(facturaProducto);
            }
    
            // Vaciar el carrito
            carritoService.vaciarCarrito(usuario.getCarrito().getId());
    
            // Redirigir a /api/productos
            return ResponseEntity.status(302).header("Location", "/api/productos").build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(createErrorResponse("Error interno del servidor: " + e.getMessage()));
        }
    }

    private BigDecimal aplicarDescuento(BigDecimal precio, DescuentoDTO descuento, int cantidad) {
        if (descuento == null || cantidad < descuento.getCantidadMinima()) {
            return precio;
        }
        BigDecimal porcentajeDescuento = BigDecimal.valueOf(descuento.getPorcentaje()).divide(new BigDecimal(100));
        BigDecimal descuentoAplicado = precio.multiply(porcentajeDescuento);
        return precio.subtract(descuentoAplicado);
    }

    private Long obtenerUserIdDeSesion() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            Usuario usuario = usuarioService.findByUsername(username);
            return usuario != null ? usuario.getUsuid() : null;
        }
        return null;
    }

    private Map<String, String> createErrorResponse(String message) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", message);
        return errorResponse;
    }
}