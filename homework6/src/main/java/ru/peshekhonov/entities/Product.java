package ru.peshekhonov.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAllProducts", query = "select p from Product p"),
        @NamedQuery(name = "deleteProductById", query = "delete from Product p where p.id=:id"),
        @NamedQuery(name = "getItemsByProductId", query = "select p from Product p join fetch p.items where p.id=:id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, precision = 8, scale = 2)
    @ToString.Exclude
    private BigDecimal cost;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Item> items;

    public Product(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }
}
