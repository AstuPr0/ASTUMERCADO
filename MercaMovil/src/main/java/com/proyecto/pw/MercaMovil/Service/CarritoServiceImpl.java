package com.proyecto.pw.MercaMovil.Service;

import com.proyecto.pw.MercaMovil.DTO.*;
import com.proyecto.pw.MercaMovil.Model.*;
import com.proyecto.pw.MercaMovil.Repository.CarritoRepository;
import com.proyecto.pw.MercaMovil.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para manejar la lógica de negocio relacionada con el carrito de compras.
 */
@Service
public class CarritoServiceImpl implements CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene un carrito por la ID del usuario.
     *
     * @param usuarioId La ID del usuario.
     * @return El DTO del carrito encontrado.
     */
    @Override
    @Transactional
    public CarritoDTO getCarrito(Long usuarioId) {
        Carrito carrito = carritoRepository.findByUsuario_Usuid(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito not found for user: " + usuarioId));

        carrito.getProductos().size();

        BigDecimal total = carrito.getProductos().stream()
                .map(carritoProducto -> {
                    BigDecimal precio = BigDecimal.valueOf(carritoProducto.getProducto().getPrice());
                    Descuento descuento = carritoProducto.getProducto().getDescuento();
                    if (descuento != null && carritoProducto.getCantidad() >= descuento.getCantidadMinima()) {
                        BigDecimal porcentajeDescuento = BigDecimal.valueOf(descuento.getPorcentajeDescuento()).divide(new BigDecimal(100));
                        BigDecimal descuentoAplicado = precio.multiply(porcentajeDescuento);
                        precio = precio.subtract(descuentoAplicado);
                    }
                    return precio.multiply(BigDecimal.valueOf(carritoProducto.getCantidad()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarritoDTO(carrito.getId(),
                convertToUsuarioDTO(carrito.getUsuario()),
                carrito.getProductos().stream()
                        .map(this::convertToCarritoProductoDTO)
                        .collect(Collectors.toList()),
                total,
                carrito.getEstado());
    }

    /**
     * Añade productos al carrito.
     *
     * @param carritoDTO El DTO del carrito que contiene los productos a añadir.
     * @return El DTO del carrito actualizado.
     */
    @Override
    @Transactional
    public CarritoDTO addToCarrito(CarritoDTO carritoDTO) {
        Carrito carrito = carritoRepository.findByUsuario_Usuid(carritoDTO.getUsuario().getId())
                .orElse(new Carrito());
        carrito.setUsuario(convertToUsuario(carritoDTO.getUsuario()));
        carrito.setEstado(carritoDTO.getEstado());

        List<CarritoProducto> carritoProductos = carritoDTO.getProductos().stream()
                .map(carritoProductoDTO -> {
                    if (carritoProductoDTO.getProducto() == null || carritoProductoDTO.getProducto().getId() == null) {
                        throw new IllegalArgumentException("El producto no puede ser nulo y debe tener un ID");
                    }
                    Producto producto = productoRepository.findById(carritoProductoDTO.getProducto().getId())
                            .orElseThrow(() -> new RuntimeException("Producto not found: " + carritoProductoDTO.getProducto().getId()));

                    CarritoProducto carritoProducto = new CarritoProducto();
                    carritoProducto.setProducto(producto);
                    carritoProducto.setCantidad(carritoProductoDTO.getCantidad());
                    carritoProducto.setCarrito(carrito);
                    return carritoProducto;
                })
                .collect(Collectors.toList());

        carrito.getProductos().addAll(carritoProductos);

        Carrito savedCarrito = carritoRepository.save(carrito);

        BigDecimal total = savedCarrito.getProductos().stream()
                .map(carritoProducto -> {
                    BigDecimal precio = BigDecimal.valueOf(carritoProducto.getProducto().getPrice());
                    Descuento descuento = carritoProducto.getProducto().getDescuento();
                    if (descuento != null && carritoProducto.getCantidad() >= descuento.getCantidadMinima()) {
                        BigDecimal porcentajeDescuento = BigDecimal.valueOf(descuento.getPorcentajeDescuento()).divide(new BigDecimal(100));
                        BigDecimal descuentoAplicado = precio.multiply(porcentajeDescuento);
                        precio = precio.subtract(descuentoAplicado);
                    }
                    return precio.multiply(BigDecimal.valueOf(carritoProducto.getCantidad()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarritoDTO(savedCarrito.getId(),
                convertToUsuarioDTO(savedCarrito.getUsuario()),
                savedCarrito.getProductos().stream()
                        .map(this::convertToCarritoProductoDTO)
                        .collect(Collectors.toList()),
                total,
                savedCarrito.getEstado());
    }

    /**
     * Vacía el carrito de un usuario.
     *
     * @param usuarioId La ID del usuario.
     */
    @Override
    @Transactional
    public void vaciarCarrito(Long usuarioId) {
        Carrito carrito = carritoRepository.findByUsuario_Usuid(usuarioId)
                .orElseThrow(() -> new RuntimeException("Carrito not found for user: " + usuarioId));

        carrito.getProductos().clear();
        carritoRepository.save(carrito);
    }

    /**
     * Realiza la compra del carrito.
     *
     * @param carritoDTO El DTO del carrito que se va a comprar.
     */
    @Override
    public void checkout(CarritoDTO carritoDTO) {
        // Implementación del método de checkout
    }

    /**
     * Obtiene todos los carritos.
     *
     * @return Una lista de DTOs de carritos.
     */
    @Transactional(readOnly = true)
    public List<CarritoDTO> findAllCarritos() {
        List<Carrito> carritos = carritoRepository.findAll();
        return carritos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Convierte un Carrito a CarritoDTO.
     *
     * @param carrito El carrito.
     * @return El DTO del carrito convertido.
     */
    private CarritoDTO convertToDTO(Carrito carrito) {
        BigDecimal total = carrito.getProductos().stream()
                .map(carritoProducto -> {
                    BigDecimal precio = BigDecimal.valueOf(carritoProducto.getProducto().getPrice());
                    Descuento descuento = carritoProducto.getProducto().getDescuento();
                    if (descuento != null && carritoProducto.getCantidad() >= descuento.getCantidadMinima()) {
                        BigDecimal porcentajeDescuento = BigDecimal.valueOf(descuento.getPorcentajeDescuento()).divide(new BigDecimal(100));
                        BigDecimal descuentoAplicado = precio.multiply(porcentajeDescuento);
                        precio = precio.subtract(descuentoAplicado);
                    }
                    return precio.multiply(BigDecimal.valueOf(carritoProducto.getCantidad()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CarritoDTO(
                carrito.getId(),
                convertToUsuarioDTO(carrito.getUsuario()),
                carrito.getProductos().stream()
                        .map(this::convertToCarritoProductoDTO)
                        .collect(Collectors.toList()),
                total,
                carrito.getEstado()
        );
    }

    /**
     * Convierte un UsuarioRegistroDTO a Usuario.
     *
     * @param usuarioDTO El DTO del usuario.
     * @return El usuario convertido.
     */
    private Usuario convertToUsuario(UsuarioRegistroDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsuid(usuarioDTO.getId());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setUseremail(usuarioDTO.getEmail());
        return usuario;
    }

    /**
     * Convierte un Usuario a UsuarioRegistroDTO.
     *
     * @param usuario El usuario.
     * @return El DTO del usuario convertido.
     */
    private UsuarioRegistroDTO convertToUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        UsuarioRegistroDTO usuarioDTO = new UsuarioRegistroDTO();
        usuarioDTO.setId(usuario.getUsuid());
        usuarioDTO.setUsername(usuario.getUsername());
        usuarioDTO.setEmail(usuario.getUseremail());
        usuarioDTO.setPassword(usuario.getUsu_password()); // Asegúrate de que el campo password esté presente si es necesario
        return usuarioDTO;
    }

    /**
     * Convierte un CarritoProducto a CarritoProductoDTO.
     *
     * @param carritoProducto El carritoProducto.
     * @return El DTO del carritoProducto convertido.
     */
    private CarritoProductoDTO convertToCarritoProductoDTO(CarritoProducto carritoProducto) {
        if (carritoProducto.getProducto() == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }

        Producto producto = carritoProducto.getProducto();
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setName(producto.getName());
        productoDTO.setPrice(producto.getPrice());
        productoDTO.setCategoria(convertToCategoriaDTO(producto.getCategoria()));
        productoDTO.setDescuento(convertToDescuentoDTO(producto.getDescuento()));
        Descuento descuento = producto.getDescuento();
        if (descuento != null) {
            productoDTO.setDescuentos(List.of(descuento.getId()));
        } else {
            productoDTO.setDescuentos(List.of());
        }
        productoDTO.setImage(producto.getImage());
        productoDTO.setEstado(producto.getEstado());

        CarritoProductoDTO carritoProductoDTO = new CarritoProductoDTO();
        carritoProductoDTO.setProducto(productoDTO);
        carritoProductoDTO.setCantidad(carritoProducto.getCantidad());
        return carritoProductoDTO;
    }

    /**
     * Convierte una Categoria a CategoriaDTO.
     *
     * @param categoria La categoría.
     * @return El DTO de la categoría convertido.
     */
    private CategoriaDTO convertToCategoriaDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        categoriaDTO.setId(categoria.getId());
        categoriaDTO.setNombre(categoria.getNombre());
        return categoriaDTO;
    }

    /**
     * Convierte un Descuento a DescuentoDTO.
     *
     * @param descuento El descuento.
     * @return El DTO del descuento convertido.
     */
    private DescuentoDTO convertToDescuentoDTO(Descuento descuento) {
        if (descuento == null) {
            return null;
        }
        DescuentoDTO descuentoDTO = new DescuentoDTO();
        descuentoDTO.setId(descuento.getId());
        descuentoDTO.setNombre(descuento.getNombre());
        descuentoDTO.setPorcentaje(descuento.getPorcentajeDescuento());
        descuentoDTO.setCantidadMinima(descuento.getCantidadMinima());
        return descuentoDTO;
    }
}