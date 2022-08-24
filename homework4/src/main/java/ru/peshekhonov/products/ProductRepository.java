package ru.peshekhonov.products;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong number = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.addProduct("Укроп", new BigDecimal(40),
                LocalDate.of(2022, 8, 28), "supplier1@yandex.ru", 12);
        this.addProduct("Редис", new BigDecimal(130),
                LocalDate.of(2022, 9, 25), "supplier1@yandex.ru", 5);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Optional<Product> findById(long id) {
        return Optional.ofNullable(products.get(id));
    }

    public void addProduct(String title, BigDecimal cost, LocalDate date, String email, long quantity) {
        long id = number.incrementAndGet();
        products.put(id, new Product(id, title, cost, date, email, quantity));
    }

    public void saveProduct(Product product) {
        long id = product.getId();
        if (id == 0) {
            id = number.incrementAndGet();
            product.setId(id);
        }
        products.put(id, product);
    }

    public void delete(long id) {
        products.remove(id);
    }
}
