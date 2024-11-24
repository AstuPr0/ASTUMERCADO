package com.proyecto.pw.MercaMovil.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.pw.MercaMovil.DTO.CategoriaDTO;
import com.proyecto.pw.MercaMovil.DTO.DescuentoDTO;
import com.proyecto.pw.MercaMovil.DTO.ProductoDTO;
import com.proyecto.pw.MercaMovil.Model.Categoria;
import com.proyecto.pw.MercaMovil.Model.Descuento;
import com.proyecto.pw.MercaMovil.Model.Producto;
import com.proyecto.pw.MercaMovil.Repository.CategoriaRepository;
import com.proyecto.pw.MercaMovil.Repository.DescuentoRepository;
import com.proyecto.pw.MercaMovil.Repository.ProductoRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

/**
 * Implementación del servicio de gestión de productos.
 * Proporciona métodos para crear, obtener, actualizar y eliminar productos.
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final DescuentoRepository descuentoRepository;

    /**
     * Constructor de ProductoServiceImpl.
     *
     * @param productoRepository el repositorio de productos
     * @param categoriaRepository el repositorio de categorías
     * @param descuentoRepository el repositorio de descuentos
     */
    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, DescuentoRepository descuentoRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.descuentoRepository = descuentoRepository;
    }

    /**
     * Crea un nuevo producto.
     *
     * @param productoDTO el DTO del producto a crear
     * @return el DTO del producto creado
     * @throws IllegalArgumentException si la categoría no se encuentra
     */
    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        // Validar y convertir el DTO a una entidad Producto
        Categoria categoria = categoriaRepository.findById(productoDTO.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));

        Descuento descuento = null;
        if (productoDTO.getDescuento() != null && productoDTO.getDescuento().getId() != null) {
            descuento = descuentoRepository.findById(productoDTO.getDescuento().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Descuento no encontrado"));
        }

        Producto producto = new Producto();
        producto.setName(productoDTO.getName());
        producto.setPrice(productoDTO.getPrice());
        producto.setCategoria(categoria);
        producto.setDescuento(descuento);
        producto.setImage(productoDTO.getImage());
        producto.setEstado(productoDTO.isEstado());

        Producto productoGuardado = productoRepository.save(producto);
        return convertirAProductoDTO(productoGuardado);
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param id el ID del producto a obtener
     * @return el DTO del producto obtenido, o null si no se encuentra
     */
    @Override
    public ProductoDTO obtenerProductoPorId(Long id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(this::convertirAProductoDTO).orElse(null);
    }

    /**
     * Obtiene todos los productos.
     *
     * @return una lista de DTOs de todos los productos
     */
    @Override
    public List<ProductoDTO> obtenerTodosLosProductos() {
        return productoRepository.findAll().stream()
                .map(this::convertirAProductoDTO)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un producto existente.
     *
     * @param productoDTO el DTO del producto a actualizar
     * @return el DTO del producto actualizado
     * @throws IllegalArgumentException si la categoría no se encuentra
     */
    @Override
    public ProductoDTO actualizarProducto(ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(productoDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    
        Categoria categoria = categoriaRepository.findById(productoDTO.getCategoria().getId())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada"));
    
        Descuento descuento = null;
        if (productoDTO.getDescuento() != null && productoDTO.getDescuento().getId() != null) {
            descuento = descuentoRepository.findById(productoDTO.getDescuento().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Descuento no encontrado"));
        }
    
        producto.setName(productoDTO.getName());
        producto.setPrice(productoDTO.getPrice());
        producto.setCategoria(categoria);
        producto.setDescuento(descuento);
        producto.setImage(productoDTO.getImage());
        producto.setEstado(productoDTO.isEstado());
    
        Producto productoActualizado = productoRepository.save(producto);
        return convertirAProductoDTO(productoActualizado);
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id el ID del producto a eliminar
     */
    @Override
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    
        productoRepository.delete(producto);
    }

    /**
     * Convierte una entidad Producto a un DTO ProductoDTO.
     *
     * @param producto la entidad Producto a convertir
     * @return el DTO ProductoDTO resultante
     */
    private ProductoDTO convertirAProductoDTO(Producto producto) {
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setName(producto.getName());
        productoDTO.setPrice(producto.getPrice());
        productoDTO.setCategoria(new CategoriaDTO(producto.getCategoria().getId(), producto.getCategoria().getNombre()));
        productoDTO.setDescuento(producto.getDescuento() != null ? new DescuentoDTO(
                producto.getDescuento().getId(),
                producto.getDescuento().getNombre(),
                producto.getDescuento().getPorcentajeDescuento(),
                producto.getDescuento().getCantidadMinima()) : null);
        productoDTO.setImage(producto.getImage());
        productoDTO.setEstado(producto.getEstado());
        return productoDTO;
    }

    /**
     * Obtiene los productos más vendidos.
     *
     * @return una lista de productos
     */
    @Transactional(readOnly = true)
    public List<Producto> findTopProductosInCarrito() {
        return productoRepository.findTopProductosInCarrito();
    }
}