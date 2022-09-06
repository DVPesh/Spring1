package ru.peshekhonov.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "line_items")
@NamedQueries({
        @NamedQuery(name = "findAllItems", query = "select i from Item i"),
        @NamedQuery(name = "deleteItemById", query = "delete from Item i where i.id=:id")
})
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private float quantity;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal cost;

    @Basic(optional = false)
    private Timestamp timestamp;

    public Item(Product product, Customer customer, float quantity, BigDecimal cost) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
        this.cost = cost;
        this.timestamp = Timestamp.from(Instant.now());
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", product=" + product.getId() +
                ", customer=" + customer.getId() +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", timestamp=" + timestamp +
                '}';
    }
}
