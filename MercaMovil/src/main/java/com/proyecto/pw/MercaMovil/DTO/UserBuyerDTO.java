package com.proyecto.pw.MercaMovil.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para la transferencia de datos del comprador para Mercado Pago.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBuyerDTO {
    
    /** Identificador del usuario. */
    private Long userId;

    /** Título del producto. */
    private String title;

    /** Cantidad del producto. */
    private int quantity;

    /** Precio unitario del producto. */
    private int unit_price;
}
