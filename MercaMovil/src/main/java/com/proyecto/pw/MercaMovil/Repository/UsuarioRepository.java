package com.proyecto.pw.MercaMovil.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.pw.MercaMovil.Model.Usuario;

import java.util.List;

/**
 * Repositorio para la entidad Tratamiento.
 * Proporciona métodos para acceder y manipular los datos relacionados con los usuarios en la base de datos.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Encuentra un usuario por su identificador.
     *
     * @param usuid Identificador del usuario.
     * @return El usuario correspondiente al identificador.
     */
    Usuario findByUsuid(Long usuid);

    /**
     * Encuentra un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario.
     * @return El usuario correspondiente al nombre de usuario.
     */
    Usuario findByUsername(String username);

    /**
     * Consulta personalizada para obtener todos los usuarios con un rol específico.
     *
     * @param role Nombre del rol.
     * @return Lista de usuarios que tienen el rol especificado.
     */
    @Query("SELECT u FROM Usuario u JOIN u.roles r WHERE r.rol_nombre = :role")
    List<Usuario> findAllByRole(String role);
}