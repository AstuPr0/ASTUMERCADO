package com.proyecto.pw.MercaMovil.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.pw.MercaMovil.Model.Rol;

/**
 * Interfaz que define un repositorio para la entidad Rol.
 * Extiende JpaRepository para obtener métodos CRUD básicos.
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    /**
     * Consulta personalizada para encontrar un rol por su nombre.
     *
     * @param rolNombre Nombre del rol a buscar.
     * @return Rol encontrado, o null si no se encuentra.
     */
    @Query("SELECT r FROM Rol r WHERE r.rol_nombre = ?1")
    Rol findByName(String rolNombre);
}