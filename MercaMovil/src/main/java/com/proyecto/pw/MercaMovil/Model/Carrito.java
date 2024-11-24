package com.proyecto.pw.MercaMovil.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa un carrito de compras en el sistema.
 * Esta clase es una entidad JPA que se mapea a la tabla "carrito" en la base de datos.
 */
@Entity
@Table(name = "carrito")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Carrito {

    /** Identificador único del carrito. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carrito_id")
    private Long id;

    /** Usuario al que pertenece el carrito. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /** Lista de productos en el carrito con sus cantidades. */
    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarritoProducto> productos;

    /** Estado del carrito (activo, completado, etc.). */
    @Column(name = "estado")
    @NotNull
    private String estado;

    /**
     * Constructor que acepta un Usuario.
     *
     * @param usuario El usuario al que pertenece el carrito.
     */
    public Carrito(Usuario usuario) {
        this.usuario = usuario;
        this.estado = "ACTIVO";
    }


}