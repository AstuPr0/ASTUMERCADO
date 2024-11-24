package com.proyecto.pw.MercaMovil.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.pw.MercaMovil.Model.Categoria;

/**
 * Interfaz que define un repositorio para la entidad Categoria.
 * Extiende JpaRepository para obtener funcionalidades de un repositorio JPA básico.
 */
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    /**
     * Busca una categoría por su identificador.
     * @param id Identificador de la categoría a buscar.
     * @return La categoría encontrada o null si no se encuentra.
     */
    Categoria findById(long id);
    

    /**
     * Busca una categoría por su nombre.
     * @param nombre Nombre de la categoría a buscar.
     * @return La categoría encontrada, o null si no se encuentra.
     */
    @Query("SELECT c FROM Categoria c WHERE c.nombre = :nombre")
    Categoria findByCategoria(String nombre);

}
