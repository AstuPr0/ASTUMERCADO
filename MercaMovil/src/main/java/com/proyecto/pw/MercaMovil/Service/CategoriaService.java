package com.proyecto.pw.MercaMovil.Service;

import com.proyecto.pw.MercaMovil.DTO.CategoriaDTO;
import java.util.List;


/**
 * Interfaz para el servicio de categorias.
 */
public interface CategoriaService {

    /**
     * Obtiene todos las categorias.
     *
     * @return Una lista de DTOs de categorias.
     */
    List<CategoriaDTO> getAllCategorias();

}
