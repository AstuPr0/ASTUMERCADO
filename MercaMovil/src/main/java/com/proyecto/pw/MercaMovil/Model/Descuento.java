package com.proyecto.pw.MercaMovil.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un descuento en el sistema.
 * Esta clase es una entidad JPA que se mapea a la tabla "descuento" en la base de datos.
 */
@Entity
@Table(name = "descuento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Descuento {

    /** Identificador único del Descuento. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "descuento_id")
    private Long id;

    /** Nombre del descuento. */
    @Column(name = "nombre")
    @NotNull
    private String nombre;

    /** Porcentaje de descuento. */
    @Column(name = "porcentaje_descuento")
    @NotNull
    private Double porcentajeDescuento;

    /** Cantidad mínima de productos para aplicar el descuento. */
    @Column(name = "cantidad_minima")
    @NotNull
    private Integer cantidadMinima;
}