package com.proyecto.pw.MercaMovil.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la categoría de un productoen el sistema.
 * Esta clase es una entidad JPA que se mapea a la tabla "categoria_producto" en la base de datos.
 */

@Entity
@Table (name = "categoria_producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
	/** Identificador único de la categoría. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Categoría del del producto, con un máximo de 50 caracteres. */
    @Column(length = 50)
    @NotNull
    @Size(max = 50)
    private String nombre;

    /** Lista de productos asociados a la categoría. */
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos;
}
