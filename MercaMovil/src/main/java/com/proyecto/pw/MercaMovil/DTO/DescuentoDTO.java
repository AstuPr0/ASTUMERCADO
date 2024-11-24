package com.proyecto.pw.MercaMovil.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un DTO (Data Transfer Object) para la entidad Descuento.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DescuentoDTO {

    /** Identificador del descuento. */
    private Long id;

    /** Nombre del descuento. */
    private String nombre;

    /** Porcentaje del descuento. */
    private Double porcentaje;

    /** Cantidad mínima para aplicar el descuento. */
    private Integer cantidadMinima;
}