package com.proyecto.pw.MercaMovil.DTO;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object para la entidad Carrito.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarritoDTO {

    /** Identificador único del Carrito. */
    private Long id;

    /** Usuario al que pertenece el carrito. */
    private UsuarioRegistroDTO usuario;

    /** Lista de productos en el carrito. */
    private List<CarritoProductoDTO> productos;

    /** Total del carrito. */
    private BigDecimal total;

    /** Estado del carrito (activo, completado, etc.). */
    private String estado;
}