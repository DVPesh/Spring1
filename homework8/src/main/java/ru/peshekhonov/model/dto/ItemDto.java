package ru.peshekhonov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private ProductDto product;
    private long quantity;
    private CartDto cart;
    private BigDecimal cost;

    public ItemDto(ProductDto product, long quantity, CartDto cart, BigDecimal cost) {
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
        this.cost = cost;
    }
}
