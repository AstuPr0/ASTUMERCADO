package com.proyecto.pw.MercaMovil.DTO;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un DTO (Data Transfer Object) para la entidad Producto.
 * Esta clase se utiliza para transferir datos relacionados con un producto entre capas de la aplicación.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    /** Identificador del producto. */
    private Long id;

    /** Nombre del producto. */
    @NotNull(message = "El nombre no puede ser nulo")
    private String name;

    /** Precio del producto. */
    @NotNull(message = "El precio no puede ser nulo")
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double price;

    /** Categoría del producto. */
    @NotNull(message = "La categoría no puede ser nula")
    private CategoriaDTO categoria;

    /** Descuento del producto. */
    private DescuentoDTO descuento;

    /** Lista de identificadores de Descuentos asociados al producto. */
    private List<Long> descuentos;

    /** Imagen del producto en formato byte[]. */
    private byte[] image;
    
    /** Estado del producto (activo o inactivo). */
    private boolean estado;
}