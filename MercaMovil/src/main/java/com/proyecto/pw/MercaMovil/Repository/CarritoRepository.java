package com.proyecto.pw.MercaMovil.Repository;

import com.proyecto.pw.MercaMovil.Model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    
    Optional<Carrito> findByUsuario_Usuid(Long usuarioId);
    
    List<Carrito> findAll();
}