package com.proyecto.pw.MercaMovil.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * Clase que representa un producto en el carrito de un usuario.
 * Esta clase es una entidad JPA que se mapea a la tabla "carrito_producto" en la base de datos.
 */ 
@Entity
@Table(name = "carrito_producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarritoProducto implements Serializable {

    /** Identificador único del producto en el carrito. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carrito_producto_id")
    private Long id;

    /** Carrito al que pertenece el producto. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carrito_id", nullable = false)
    private Carrito carrito;

    /** Producto en el carrito. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    /** Cantidad del producto en el carrito. */
    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;
}