package com.proyecto.pw.MercaMovil.Model;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa a un producto en el sistema.
 * Esta clase es una entidad JPA que se mapea a la tabla "producto" en la base de datos.
 */

@Entity
@Table(name = "producto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
	
	/** Identificador único del Producto. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pro_id")
    private Long id;

    /** Nombre del animal. Debe ser único. */
    @Column(name = "pro_name", unique = true)
    @NotNull
    @Size(min = 3, max = 90)
    private String name;

    /** Precio del producto. */
    @Column(name = "pro_price")
    @NotNull
    @Min(value = 0, message = "El precio debe ser mayor o igual a 0")
    private Double price;
    
    /** Categoría del producto. */
    @OneToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    /** Descuento asignado al producto. */
    @ManyToOne
    @JoinColumn(name = "descuento_id")
    private Descuento descuento;

    /** Imagen del producto en formato de arreglo de bytes. */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    /** Estado del producto. */
    @Column(name = "pro_estado")
    @NotNull
    private Boolean estado = true;

    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CarritoProducto> carritoProductos;  

}
