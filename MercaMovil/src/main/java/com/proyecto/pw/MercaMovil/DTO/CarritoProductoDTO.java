package com.proyecto.pw.MercaMovil.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa un producto en el carrito de un usuario.
 * Contiene información básica necesaria para mostrar un producto en el carrito de un usuario.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoProductoDTO {

    /** Producto en el carrito. */
    private ProductoDTO producto;

    /** Cantidad de productos en el carrito. */
    private Integer cantidad;
}