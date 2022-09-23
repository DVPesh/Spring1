package ru.peshekhonov.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "products")
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

    @OneToMany(mappedBy = "product")
    private Set<Item> items;

    public Product(String title, BigDecimal cost, LocalDate expirationDate, String supplierEmail, long quantity) {
        this.title = title;
        this.cost = cost;
        this.expirationDate = expirationDate;
        this.supplierEmail = supplierEmail;
        this.quantity = quantity;
    }
}
