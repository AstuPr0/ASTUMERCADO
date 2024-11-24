package com.proyecto.pw.MercaMovil.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que representa la información del comprador para Mercado Pago.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBuyer {

    /** Identificador del usuario. */
    private Long userId;

    /** Título del producto. */
    private String title;

    /** Cantidad del producto. */
    private int quantity;

    /** Precio unitario del producto. */
    private int unit_price;
}