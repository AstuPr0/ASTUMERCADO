package com.proyecto.pw.MercaMovil.Controller;

import com.proyecto.pw.MercaMovil.DTO.CategoriaDTO;
import com.proyecto.pw.MercaMovil.DTO.DescuentoDTO;
import com.proyecto.pw.MercaMovil.DTO.ProductoDTO;
import com.proyecto.pw.MercaMovil.Model.Usuario;
import com.proyecto.pw.MercaMovil.Service.CategoriaService;
import com.proyecto.pw.MercaMovil.Service.DescuentoService;
import com.proyecto.pw.MercaMovil.Service.ProductoService;
import com.proyecto.pw.MercaMovil.Service.UsuarioService;

import org.springframework.ui.Model;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 */
@Controller
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final UsuarioService usuarioService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private DescuentoService descuentoService;

    /**
     * Constructor de ProductoController.
     *
     * @param productoService el servicio de productos
     * @param usuarioService el servicio de usuarios
     */
    @Autowired
    public ProductoController(ProductoService productoService, UsuarioService usuarioService) {
        this.productoService = productoService;
        this.usuarioService = usuarioService;
    }

    /**
     * Crea un nuevo producto.
     *
     * @param productoDTO el DTO del producto a crear
     * @return el DTO del producto creado
     */
    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevoProducto = productoService.crearProducto(productoDTO);
        return ResponseEntity.ok(nuevoProducto);
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id el ID del producto a obtener
     * @return el DTO del producto obtenido, o 404 si no se encuentra
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        ProductoDTO producto = productoService.obtenerProductoPorId(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    /**
     * Obtiene todos los productos y los muestra en una vista JSP.
     *
     * @param model el modelo para la vista
     * @return el nombre de la vista JSP
     */
    @GetMapping
    public String obtenerTodosLosProductos(Model model, Principal principal) {
        List<ProductoDTO> productos = productoService.obtenerTodosLosProductos();
        List<CategoriaDTO> categorias = categoriaService.getAllCategorias();
        List<DescuentoDTO> descuentos = descuentoService.getAllDescuentos();

        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);
        model.addAttribute("descuentos", descuentos);

        if (principal != null) {
            String username = principal.getName();
            Usuario usuario = usuarioService.findByUsername(username);
            model.addAttribute("usuario", usuario);
        }

        return "productos";
    }

    /**
     * Actualiza un producto existente.
     *
     * @param id el ID del producto a actualizar
     * @param productoDTO el DTO del producto a actualizar
     * @return el DTO del producto actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        productoDTO.setId(id);
        ProductoDTO productoActualizado = productoService.actualizarProducto(productoDTO);
        return ResponseEntity.ok(productoActualizado);
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id el ID del producto a eliminar
     * @return una respuesta vacía con el estado HTTP 204
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}