package com.proyecto.pw.MercaMovil.DTO;

import lombok.Data;

/**
 * DTO (Data Transfer Object) utilizado para el registro de usuarios.
 * Contiene información básica necesaria para registrar un nuevo usuario en el sistema.
 */
@Data
public class UsuarioRegistroDTO {

    /** Identificador único del usuario. */
    private Long id;
    
    /** Nombre de usuario. */
    private String username;

    /** Correo electrónico del usuario. */
    private String email;

    /** Contraseña del usuario. */
    private String password;

    /**
     * Constructor con todos los atributos.
     *
     * @param id Identificador único del usuario.
     * @param username Nombre de usuario.
     * @param email Correo electrónico.
     * @param password Contraseña.
     */
    public UsuarioRegistroDTO(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor vacío por defecto.
     */
    public UsuarioRegistroDTO() {
        super();
    }
}