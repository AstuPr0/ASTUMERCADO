package com.proyecto.pw.MercaMovil.Controller;

import com.proyecto.pw.MercaMovil.DTO.CarritoDTO;
import com.proyecto.pw.MercaMovil.Service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para manejar las operaciones relacionadas con el carrito de compras.
 */
@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    /**
     * Obtiene un carrito por la ID del usuario.
     *
     * @param id La ID del usuario.
     * @return El DTO del carrito encontrado.
     */
    @GetMapping("/{id}")
    public CarritoDTO getCarrito(@PathVariable("id") Long id) {
        return carritoService.getCarrito(id);
    }

    /**
     * Añade productos al carrito.
     *
     * @param carritoDTO El DTO del carrito que contiene los productos a añadir.
     * @return El DTO del carrito actualizado.
     */
    @PostMapping("/add")
    public CarritoDTO addToCarrito(@RequestBody CarritoDTO carritoDTO) {
        return carritoService.addToCarrito(carritoDTO);
    }

    /**
     * Vacía el carrito de un usuario.
     *
     * @param id La ID del usuario.
     */
    @DeleteMapping("/vaciar/{id}")
    public void vaciarCarrito(@PathVariable("id") Long id) {
        carritoService.vaciarCarrito(id);
    }

    /**
     * Realiza la compra del carrito.
     *
     * @param carritoDTO El DTO del carrito que se va a comprar.
     */
    @PostMapping("/checkout")
    public void checkout(@RequestBody CarritoDTO carritoDTO) {
        carritoService.checkout(carritoDTO);
    }
    
}