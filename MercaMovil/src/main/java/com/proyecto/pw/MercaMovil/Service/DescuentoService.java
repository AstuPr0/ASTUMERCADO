package com.proyecto.pw.MercaMovil.Service;

import com.proyecto.pw.MercaMovil.DTO.DescuentoDTO;
import java.util.List;

/**
 * Interfaz para el servicio de descuentos.
 */
public interface DescuentoService {

    /**
     * Crea un nuevo descuento.
     *
     * @param descuentoDTO El DTO del descuento a crear.
     * @return El DTO del descuento creado.
     */
    DescuentoDTO createDescuento(DescuentoDTO descuentoDTO);

    /**
     * Actualiza un descuento existente.
     *
     * @param id El identificador del descuento a actualizar.
     * @param descuentoDTO El DTO del descuento actualizado.
     * @return El DTO del descuento actualizado.
     */
    DescuentoDTO updateDescuento(Long id, DescuentoDTO descuentoDTO);

    /**
     * Elimina un descuento por su identificador.
     *
     * @param id El identificador del descuento a eliminar.
     */
    void deleteDescuento(Long id);

    /**
     * Obtiene todos los descuentos.
     *
     * @return Una lista de DTOs de descuentos.
     */
    List<DescuentoDTO> getAllDescuentos();

    /**
     * Obtiene un descuento por su ID.
     * 
     * @param id el ID del descuento a obtener
     * @return el DTO del descuento
     */
    DescuentoDTO getDescuentoById(Long id);
}