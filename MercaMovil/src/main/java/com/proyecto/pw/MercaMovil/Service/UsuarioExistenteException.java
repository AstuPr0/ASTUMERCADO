package com.proyecto.pw.MercaMovil.Service;

/**
 * Excepción personalizada para el caso de usuario existente.
 * Esta excepción se utiliza cuando se intenta crear un usuario que ya existe en el sistema.
 */
public class UsuarioExistenteException extends Exception {

    /**
     * Constructor que recibe un mensaje de error.
     *
     * @param message El mensaje que describe la causa de la excepción.
     */
    public UsuarioExistenteException(String message) {
        // Llama al constructor de la clase padre (Exception) con el mensaje proporcionado.
        super(message);
    }
}