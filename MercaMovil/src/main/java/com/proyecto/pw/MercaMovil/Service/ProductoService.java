package com.proyecto.pw.MercaMovil.Service;

import com.proyecto.pw.MercaMovil.DTO.ProductoDTO;
import com.proyecto.pw.MercaMovil.Model.Producto;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * Interface para el servicio de gestión de productos.
 * Proporciona métodos para crear, obtener, actualizar y eliminar productos.
 */
public interface ProductoService {

    /**
     * Crea un nuevo producto.
     *
     * @param productoDTO el DTO del producto a crear
     * @return el DTO del producto creado
     */
    ProductoDTO crearProducto(ProductoDTO productoDTO);

    /**
     * Obtiene un producto por su ID.
     *
     * @param id el ID del producto a obtener
     * @return el DTO del producto obtenido, o null si no se encuentra
     */
    ProductoDTO obtenerProductoPorId(Long id);

    /**
     * Obtiene todos los productos.
     *
     * @return una lista de DTOs de todos los productos
     */
    List<ProductoDTO> obtenerTodosLosProductos();

    /**
     * Actualiza un producto existente.
     *
     * @param productoDTO el DTO del producto a actualizar
     * @return el DTO del producto actualizado
     */
    ProductoDTO actualizarProducto(ProductoDTO productoDTO);

    /**
     * Elimina un producto por su ID.
     *
     * @param id el ID del producto a eliminar
     */
    void eliminarProducto(Long id);

    /**
     * Obtiene los productos más vendidos.
     *
     * @return una lista de productos
     */
    @Transactional(readOnly = true)
    public List<Producto> findTopProductosInCarrito();
}