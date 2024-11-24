package com.proyecto.pw.MercaMovil.Service;

import java.util.List;

import com.proyecto.pw.MercaMovil.DTO.FacturaDTO;

public interface FacturaService {
    List<FacturaDTO> findAllFacturas();
    List<FacturaDTO> findFacturasByUsuarioId(Long usuarioId);
}