package com.proyecto.pw.MercaMovil.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.proyecto.pw.MercaMovil.Model.FacturaProducto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacturaDTO {
    private Long id;
    private String usuario;
    private List<FacturaProducto> productos;
    private BigDecimal total;
    private LocalDateTime fecha;
    private String estado;
    private String externalReference;
    private String paymentId;
}