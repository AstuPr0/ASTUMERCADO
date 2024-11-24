package com.proyecto.pw.MercaMovil.Service;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.proyecto.pw.MercaMovil.DTO.UsuarioRegistroDTO;
import com.proyecto.pw.MercaMovil.Model.Usuario;

/**
 * Interfaz que define los servicios relacionados con los usuarios en el sistema.
 */
public interface UsuarioService extends UserDetailsService {

	/**
	 * Método para guardar un nuevo usuario en el sistema.
	 *
	 * @param registroDTO Objeto DTO que contiene la información del usuario a registrar.
	 * @return El usuario guardado en la base de datos.
	 * @throws UsuarioExistenteException Si el usuario ya existe en el sistema.
	 * @throws Exception Si ocurre un error durante el proceso de registro.
	 */
	public Usuario guardar(UsuarioRegistroDTO registroDTO) throws UsuarioExistenteException, Exception;
	
	/**
	 * Método para obtener una lista de todos los usuarios registrados en el sistema.
	 *
	 * @return Lista de usuarios registrados.
	 */
	public List<Usuario> listarUsuarios();
	
	/**
	 * Método para encontrar un usuario por su nombre de usuario.
	 *
	 * @param username Nombre de usuario.
	 * @return El usuario encontrado.
	 */
	Usuario findByUsername(String username);

	/**
	 * Metodo para encontrar un usuario por su id.
	 * @return El usuario encontrado.
	 * @param id
	 */
	Usuario findById(Long id);

    /**
     * Método para obtener todos los usuarios registrados en el sistema.
     *
     * @return Lista de todos los usuarios.
     */
	public List<Usuario> getAllUsuarios();
	
	/**
	 * Método para encontrar todos los usuarios que tengan un rol específico.
	 *
	 * @param role Rol de usuario a buscar.
	 * @return Lista de usuarios que tienen el rol especificado.
	 */
	public List<Usuario> findAllByRole(String role);

    /**
     * Método para obtener un usuario por su ID.
     *
     * @param userId ID del usuario.
     * @return El usuario encontrado.
     */
	public Usuario getUserById(Long userId);
}