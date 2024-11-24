package com.proyecto.pw.MercaMovil.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.proyecto.pw.MercaMovil.DTO.DescuentoDTO;
import com.proyecto.pw.MercaMovil.Service.DescuentoService;
import java.util.List;

/**
 * Controlador para manejar las operaciones relacionadas con los descuentos.
 */
@RestController
@RequestMapping("/api/descuentos")
public class DescuentoController {

    @Autowired
    private DescuentoService descuentoService;

    /**
     * Crea un nuevo descuento.
     *
     * @param descuentoDTO El DTO del descuento a crear.
     * @return El DTO del descuento creado.
     */
    @PostMapping
    public ResponseEntity<DescuentoDTO> createDescuento(@RequestBody DescuentoDTO descuentoDTO) {
        DescuentoDTO createdDescuento = descuentoService.createDescuento(descuentoDTO);
        return ResponseEntity.ok(createdDescuento);
    }

    /**
     * Actualiza un descuento existente.
     *
     * @param id El identificador del descuento a actualizar.
     * @param descuentoDTO El DTO del descuento actualizado.
     * @return El DTO del descuento actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DescuentoDTO> updateDescuento(@PathVariable Long id, @RequestBody DescuentoDTO descuentoDTO) {
        DescuentoDTO updatedDescuento = descuentoService.updateDescuento(id, descuentoDTO);
        return ResponseEntity.ok(updatedDescuento);
    }

    /**
     * Elimina un descuento por su identificador.
     *
     * @param id El identificador del descuento a eliminar.
     * @return Una respuesta vacía con estado 204 No Content.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDescuento(@PathVariable Long id) {
        descuentoService.deleteDescuento(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtiene todos los descuentos.
     *
     * @return Una lista de DTOs de descuentos.
     */
    @GetMapping
    public ResponseEntity<List<DescuentoDTO>> getAllDescuentos() {
        List<DescuentoDTO> descuentos = descuentoService.getAllDescuentos();
        return ResponseEntity.ok(descuentos);
    }

    /**
     * Obtiene un descuento por su ID.
     *
     * @param id El ID del descuento a obtener.
     * @return El DTO del descuento encontrado, o 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DescuentoDTO> getDescuentoById(@PathVariable Long id) {
        DescuentoDTO descuento = descuentoService.getDescuentoById(id);
        return descuento != null ? ResponseEntity.ok(descuento) : ResponseEntity.notFound().build();
    }
}