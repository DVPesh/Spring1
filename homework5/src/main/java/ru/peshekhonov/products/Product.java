package ru.peshekhonov.products;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@ToString
@Setter
@Getter
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "select p from Product p"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id=:id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal cost;

    @Column
    private LocalDate expirationDate;

    @Column
    private String supplierEmail;

    @Column(nullable = false)
    private long quantity;

    public Product(String title, BigDecimal cost, LocalDate expirationDate, String supplierEmail, long quantity) {
        this.title = title;
        this.cost = cost;
        this.expirationDate = expirationDate;
        this.supplierEmail = supplierEmail;
        this.quantity = quantity;
    }
}
