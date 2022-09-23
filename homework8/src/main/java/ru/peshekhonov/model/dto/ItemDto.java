package ru.peshekhonov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peshekhonov.model.Cart;
import ru.peshekhonov.model.Product;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private Product product;
    private long quantity;
    private Cart cart;
    private BigDecimal cost;

    public ItemDto(Product product, long quantity, Cart cart, BigDecimal cost) {
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
        this.cost = cost;
    }
}
