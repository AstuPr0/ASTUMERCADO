package com.proyecto.pw.MercaMovil.Service;

import com.proyecto.pw.MercaMovil.DTO.CategoriaDTO;
import com.proyecto.pw.MercaMovil.Repository.CategoriaRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementación del servicio de categorías.
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
                .map(categoria -> new CategoriaDTO(
                        categoria.getId(),
                        categoria.getNombre()))
                .collect(Collectors.toList());
    }
}