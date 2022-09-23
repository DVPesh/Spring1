package ru.peshekhonov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private long quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal cost;

    public Item(Product product, long quantity, Cart cart, BigDecimal cost) {
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
        this.cost = cost;
    }
}
