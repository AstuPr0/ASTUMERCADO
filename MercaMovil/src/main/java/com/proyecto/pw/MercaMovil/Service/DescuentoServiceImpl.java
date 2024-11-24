package com.proyecto.pw.MercaMovil.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.proyecto.pw.MercaMovil.DTO.DescuentoDTO;
import com.proyecto.pw.MercaMovil.Model.Descuento;
import com.proyecto.pw.MercaMovil.Repository.DescuentoRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de descuentos.
 */
@Service
public class DescuentoServiceImpl implements DescuentoService {

    @Autowired
    private DescuentoRepository descuentoRepository;

    @Override
    public DescuentoDTO createDescuento(DescuentoDTO descuentoDTO) {
        Descuento descuento = new Descuento();
        descuento.setNombre(descuentoDTO.getNombre());
        descuento.setPorcentajeDescuento(descuentoDTO.getPorcentaje());
        descuento.setCantidadMinima(descuentoDTO.getCantidadMinima());
        descuento = descuentoRepository.save(descuento);
        descuentoDTO.setId(descuento.getId());
        return descuentoDTO;
    }

    @Override
    public DescuentoDTO updateDescuento(Long id, DescuentoDTO descuentoDTO) {
        Descuento descuento = descuentoRepository.findById(id).orElseThrow();
        descuento.setNombre(descuentoDTO.getNombre());
        descuento.setPorcentajeDescuento(descuentoDTO.getPorcentaje());
        descuento.setCantidadMinima(descuentoDTO.getCantidadMinima());
        descuento = descuentoRepository.save(descuento);
        return descuentoDTO;
    }

    @Override
    public void deleteDescuento(Long id) {
        descuentoRepository.deleteById(id);
    }

    @Override
    public List<DescuentoDTO> getAllDescuentos() {
        return descuentoRepository.findAll().stream()
                .map(descuento -> new DescuentoDTO(
                        descuento.getId(),
                        descuento.getNombre(),
                        descuento.getPorcentajeDescuento(),
                        descuento.getCantidadMinima()))
                .collect(Collectors.toList());
    }

    @Override
    public DescuentoDTO getDescuentoById(Long id) {
        Descuento descuento = descuentoRepository.findById(id).orElseThrow();
        return new DescuentoDTO(
                descuento.getId(),
                descuento.getNombre(),
                descuento.getPorcentajeDescuento(),
                descuento.getCantidadMinima());
    }
}