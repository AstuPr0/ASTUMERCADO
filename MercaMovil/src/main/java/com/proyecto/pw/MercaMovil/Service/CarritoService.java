package com.proyecto.pw.MercaMovil.Service;

import com.proyecto.pw.MercaMovil.DTO.CarritoDTO;
import java.util.List;

/**
 * Interfaz para el servicio de carrito de compras.
 */
public interface CarritoService {

    /**
     * Obtiene un carrito por su identificador.
     *
     * @param id El identificador del carrito.
     * @return El DTO del carrito encontrado.
     */
    CarritoDTO getCarrito(Long id);

    /**
     * Añade productos al carrito.
     *
     * @param carritoDTO El DTO del carrito que contiene los productos a añadir.
     * @return El DTO del carrito actualizado.
     */
    CarritoDTO addToCarrito(CarritoDTO carritoDTO);

    /**
     * Vacía el carrito de un usuario.
     *
     * @param id La ID del usuario.
     */
    void vaciarCarrito(Long id);

    /**
     * Realiza la compra del carrito.
     *
     * @param carritoDTO El DTO del carrito que se va a comprar.
     */
    void checkout(CarritoDTO carritoDTO);

    /**
     * Obtiene todos los carritos.
     * 
     * @return Una lista de DTOs de carritos.
     */
    List<CarritoDTO> findAllCarritos();
}