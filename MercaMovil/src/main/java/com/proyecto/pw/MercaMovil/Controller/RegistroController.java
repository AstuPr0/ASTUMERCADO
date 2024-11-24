package com.proyecto.pw.MercaMovil.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.proyecto.pw.MercaMovil.DTO.CarritoDTO;
import com.proyecto.pw.MercaMovil.DTO.FacturaDTO;
import com.proyecto.pw.MercaMovil.Model.Producto;
import com.proyecto.pw.MercaMovil.Model.Usuario;
import com.proyecto.pw.MercaMovil.Service.CarritoService;
import com.proyecto.pw.MercaMovil.Service.FacturaService;
import com.proyecto.pw.MercaMovil.Service.ProductoService;
import com.proyecto.pw.MercaMovil.Service.UsuarioService;

/**
 * Controlador encargado de las operaciones relacionadas con la navegación y el
 * inicio de sesión.
 */
@Controller
public class RegistroController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    /**
     * Método para manejar la petición GET a la ruta "/login".
     *
     * @return La vista "login".
     */
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    }

    /**
     * Método para redirigir a la ruta "/home" cuando se acceda a la ruta principal
     * "/".
     *
     * @return Redirección a la ruta "/home".
     */
    @GetMapping("/")
    public String home() {
        return "redirect:/home";
    }

    /**
     * Método para mostrar el índice de la aplicación.
     *
     * @param model     El modelo.
     * @param principal El objeto Principal.
     * @return La vista "index".
     */
    @GetMapping("/home")
    public String showIndex(Model model, Principal principal) {
        // Obtener y agregar el usuario al modelo si está autenticado
        if (principal != null) {
            String username = principal.getName();
            Usuario usuario = usuarioService.findByUsername(username);
            model.addAttribute("usuario", usuario);
        }
        List<Producto> topProductos = productoService.findTopProductosInCarrito();
        model.addAttribute("topProductos", topProductos);
        return "index";
    }

    /**
     * Método para manejar la petición GET a la ruta "/about".
     *
     * @param model     El modelo.
     * @param principal El objeto Principal.
     * @return La vista "about".
     */
    @GetMapping("/about")
    public String showAbout(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            Usuario usuario = usuarioService.findByUsername(username);
            model.addAttribute("usuario", usuario);
        }
        return "about";
    }

    /**
     * Método para manejar la petición GET a la ruta "/profile".
     *
     * @param model     El modelo.
     * @param principal El objeto Principal.
     * @return La vista "profile".
     */
    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            Usuario usuario = usuarioService.findByUsername(username);
            model.addAttribute("usuario", usuario);

            boolean isAdmin = usuario.getRoles().stream()
                    .anyMatch(rol -> rol.getRol_nombre().equals("ROLE_ADMIN"));

            if (isAdmin) {
                List<FacturaDTO> todasLasFacturas = facturaService.findAllFacturas();
                model.addAttribute("facturas", todasLasFacturas);

                List<CarritoDTO> todosLosCarritos = carritoService.findAllCarritos();
                model.addAttribute("carritos", todosLosCarritos);
            } else {
                List<FacturaDTO> facturasUsuario = facturaService.findFacturasByUsuarioId(usuario.getUsuid());
                model.addAttribute("facturas", facturasUsuario);
            }
        }

        return "profile";
    }
}