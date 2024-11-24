package com.proyecto.pw.MercaMovil.Repository;

import com.proyecto.pw.MercaMovil.Model.FacturaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaProductoRepository extends JpaRepository<FacturaProducto, Long> {
}