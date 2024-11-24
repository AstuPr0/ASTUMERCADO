package com.proyecto.pw.MercaMovil.Repository;

import com.proyecto.pw.MercaMovil.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByUsuarioUsuid(Long usuid);
}