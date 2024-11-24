package com.proyecto.pw.MercaMovil.Repository;

import org.springframework.stereotype.Repository;
import com.proyecto.pw.MercaMovil.Model.Producto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Interfaz de repositorio para la entidad Producto.
 * Extiende JpaRepository para obtener operaciones CRUD básicas.
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    /**
     * Busca un producto por su nombre.
     * @param name Nombre del producto a buscar.
     * @return El producto encontrado, o null si no se encuentra.
     */
    Producto findByName(String name);

    /**
     * Busca un producto por su identificador.
     * @param id Identificador del producto a buscar.
     * @return El producto encontrado, o null si no se encuentra.
     */
    Producto findById(long id);

    /**
     * Busca productos por su descuento.
     * @param descuento Descuento del producto a buscar.
     * @return Lista de productos encontrados.
     */
    @Query("SELECT p FROM Producto p WHERE p.descuento = :descuento")
    List<Producto> findByDescuento(String descuento);

    /**
     * Busca productos por su categoría.
     * @param categoriaId Id de la categoría del producto a buscar.
     * @return Lista de productos encontrados.
     */
    @Query("SELECT p FROM Producto p WHERE p.categoria.id = :categoriaId")
    List<Producto> findByCategoria(long categoriaId);

    /**
     * Obtiene todos los productos.
     * @return Lista de todos los productos.
     */
    List<Producto> findAll();
    
    /**
     * Obtiene los productos más vendidos.
     * @return Lista de productos.
     */
    @Query("SELECT p FROM Producto p JOIN p.carritoProductos cp GROUP BY p ORDER BY COUNT(cp) DESC")
    List<Producto> findTopProductosInCarrito();
}