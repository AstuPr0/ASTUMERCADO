package com.proyecto.pw.MercaMovil.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un DTO (Data Transfer Object) para la entidad Categoria.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO {

    /** Identificador de la categoría. */
    private Long id;

    /** Nombre de la categoría. */
    private String nombre;
}