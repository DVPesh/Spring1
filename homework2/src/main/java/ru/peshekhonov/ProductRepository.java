package ru.peshekhonov;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = Collections.synchronizedList(new ArrayList<>());
        this.products.add(new Product(1, "Помидоры", 200));
        this.products.add(new Product(2, "Огурцы", 150));
        this.products.add(new Product(3, "Сыр", 900));
        this.products.add(new Product(4, "Говядина", 850));
        this.products.add(new Product(5, "Творог", 450));
    }

    public List<Product> getProducts() {
        synchronized (products) {
            return List.copyOf(products);
        }
    }

    public Optional<Product> getProductById(int id) {
        synchronized (products) {
            return products.stream().filter(p -> p.getId() == id).findAny();
        }
    }

    public boolean addProduct(Product product) {
        synchronized (products) {
            if (products.stream().anyMatch(p -> p.getId() == product.getId()) &&
                    !products.removeIf(p -> p.getId() == product.getId() && p.getTitle().equals(product.getTitle()))) {
                return false;
            }
        }
        this.products.add(product);
        return true;
    }

    public boolean removeProductById(int id) {
        synchronized (products) {
            return products.removeIf(p -> p.getId() == id);
        }
    }
}
