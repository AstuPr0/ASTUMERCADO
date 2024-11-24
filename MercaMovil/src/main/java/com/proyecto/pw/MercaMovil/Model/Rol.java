package com.proyecto.pw.MercaMovil.Model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

/**
 * Clase que representa un Rol en el sistema.
 * Esta clase es una entidad JPA que se mapea a la tabla "rol" en la base de datos.
 */
@Entity
@Table(name = "rol")
@Data
public class Rol {

    /** Identificador único del rol. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long rol_id;

    /** Nombre del rol, con un máximo de 20 caracteres. */
    @NotNull
    @Size(max = 20)
    private String rol_nombre;

    /** Lista de roles relacionados (autorrelación). */
    @OneToMany(mappedBy = "rol")
    @ToString.Exclude
    private List<Rol> rol;

    /**
     * Constructor con todos los atributos.
     *
     * @param rol_id Identificador del rol.
     * @param rol_nombre Nombre del rol.
     * @param rol Lista de roles relacionados.
     */
    public Rol(@NotNull Long rol_id, @NotNull @Size(max = 20) String rol_nombre, List<Rol> rol) {
        this.rol_id = rol_id;
        this.rol_nombre = rol_nombre;
        
    }

    /**
     * Constructor sin el identificador.
     *
     * @param rol_nombre Nombre del rol.
     */
    public Rol(String rol_nombre) {
        super();
        this.rol_nombre = rol_nombre;
    }

    /**
     * Constructor con el nombre del rol y la lista de roles relacionados.
     *
     * @param rol_nombre Nombre del rol.
     * @param rol Lista de roles relacionados.
     */
    public Rol(@NotNull @Size(max = 20) String rol_nombre, List<Rol> rol) {
        this.rol_nombre = rol_nombre;
        this.rol = rol;
    }

    /**
     * Constructor vacío para JPA.
     */
    public Rol() {
    }
}
