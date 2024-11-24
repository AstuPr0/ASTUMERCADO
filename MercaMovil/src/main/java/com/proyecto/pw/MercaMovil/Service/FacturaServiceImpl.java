package com.proyecto.pw.MercaMovil.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.pw.MercaMovil.DTO.FacturaDTO;
import com.proyecto.pw.MercaMovil.Model.Factura;
import com.proyecto.pw.MercaMovil.Repository.FacturaRepository;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Override
    public List<FacturaDTO> findAllFacturas() {
        List<Factura> facturas = facturaRepository.findAll();
        return facturas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<FacturaDTO> findFacturasByUsuarioId(Long usuid) {
        List<Factura> facturas = facturaRepository.findByUsuarioUsuid(usuid);
        return facturas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private FacturaDTO convertToDTO(Factura factura) {
        return new FacturaDTO(
            factura.getId(),
            factura.getUsuario().getUsername(),
            factura.getProductos(),
            factura.getTotal(),
            factura.getFecha(),
            factura.getEstado(),
            factura.getExternalReference(),
            factura.getPaymentId()
        );
    }
}