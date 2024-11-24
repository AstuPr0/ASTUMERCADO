package com.proyecto.pw.MercaMovil.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL)
    private List<FacturaProducto> productos;

    private BigDecimal total;

    private LocalDateTime fecha;

    private String estado;

    private String externalReference;

    private String paymentId;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
}