package com.proyecto.pw.MercaMovil.Model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import lombok.Data;

/**
 * Clase que representa un usuario en el sistema.
 * Esta clase es una entidad JPA que se mapea a la tabla "usuario" en la base de datos.
 */
@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    /** Identificador único del usuario. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuid")
    private long usuid;

    /** Nombre de usuario, debe ser único. */
    @Column(unique = true)
    @NotNull
    @Size(min = 8, max = 20)
    private String username;

    /** Correo electrónico del usuario, debe ser único. */
    @Column(unique = true)
    @NotNull
    @Size(min = 20, max = 100)
    private String useremail;

    /** Contraseña del usuario. */
    @NotNull
    @Size(min = 8)
    private String usu_password;

    /** Estado del usuario (ACTIVO/INACTIVO). */
    @NotNull
    @Size(max = 10)
    private String usu_estado = "ACTIVO";

    /** Roles asignados al usuario. */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuid", referencedColumnName = "usuid"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "rol_id"))
    private Collection<Rol> roles;

    /** Carrito asignado al usuario. */
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Carrito carrito;

    /**
     * Constructor con todos los atributos.
     *
     * @param usuid Identificador del usuario.
     * @param username Nombre de usuario.
     * @param useremail Correo electrónico del usuario.
     * @param usu_password Contraseña del usuario.
     * @param usu_estado Estado del usuario.
     * @param roles Roles asignados al usuario.
     */
    public Usuario(long usuid, @NotNull @Size(max = 20) String username, @NotNull @Size(max = 100) String useremail, String usu_password, @NotNull @Size(max = 10) String usu_estado, Collection<Rol> roles) {
        this.usuid = usuid;
        this.username = username;
        this.useremail = useremail;
        this.usu_password = usu_password;
        this.usu_estado = usu_estado;
        this.roles = roles;
    }

    /**
     * Constructor sin el estado ni el identificador.
     *
     * @param username Nombre de usuario.
     * @param useremail Correo electrónico del usuario.
     * @param usu_password Contraseña del usuario.
     * @param roles Roles asignados al usuario.
     */
    public Usuario(@NotNull @Size(max = 20) String username, @NotNull @Size(max = 100) String useremail, String usu_password, Collection<Rol> roles) {
        this.username = username;
        this.useremail = useremail;
        this.usu_password = usu_password;
        this.roles = roles;
    }

    /**
     * Constructor vacío para JPA.
     */
    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "usuid=" + usuid +
                ", username='" + username + '\'' +
                ", useremail='" + useremail + '\'' +
                ", usu_estado='" + usu_estado + '\'' +
                ", roles=" + roles +
                '}';
    }
}