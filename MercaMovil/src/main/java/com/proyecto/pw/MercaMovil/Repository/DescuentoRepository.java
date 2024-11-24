package com.proyecto.pw.MercaMovil.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.pw.MercaMovil.Model.Descuento;

/**
 * Interfaz que define un repositorio para la entidad Descuento.
 * Extiende JpaRepository para obtener funcionalidades de un repositorio JPA básico.
 */

@Repository
public interface DescuentoRepository extends JpaRepository<Descuento, Long> {
    

    /**
     * Busca un descuento por su nombre.
     * @param name Nombre del descuento a buscar.
     * @return El descuento encontrado, o null si no se encuentra.
     */
    @Query("SELECT d FROM Descuento d WHERE d.nombre = ?1")
    List<Descuento> findByName(String name);
}
