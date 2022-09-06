package ru.peshekhonov.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "customers")
@NamedQueries({
        @NamedQuery(name = "findAllCustomers", query = "select c from Customer c"),
        @NamedQuery(name = "deleteCustomerById", query = "delete from Customer c where c.id=:id"),
        @NamedQuery(name = "getItemsByCustomerId", query = "select c from Customer c join fetch c.items where c.id=:id")
})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Item> items;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        return id.equals(customer.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
