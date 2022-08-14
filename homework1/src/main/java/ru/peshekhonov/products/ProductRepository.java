package ru.peshekhonov.products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final AtomicLong number = new AtomicLong(0);

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(long id) {
        return products.get(id);
    }

    public long addProduct(String title, BigDecimal cost) {
        long id = number.incrementAndGet();
        products.put(id, new Product(id, title, cost));
        return id;
    }

    public void updateProduct(Product product, String title, BigDecimal cost) throws ProductException {
        long id = product.getId();
        if (!products.containsKey(id)) {
            throw new ProductException(product);
        }
        product.setTitle(title);
        product.setCost(cost);
        products.put(id, product);
    }

    public void updateProduct(Product product, String title) throws ProductException {
        long id = product.getId();
        if (!products.containsKey(id)) {
            throw new ProductException(product);
        }
        product.setTitle(title);
        products.put(id, product);
    }

    public void updateProduct(Product product, BigDecimal cost) throws ProductException {
        long id = product.getId();
        if (!products.containsKey(id)) {
            throw new ProductException(product);
        }
        product.setCost(cost);
        products.put(id, product);
    }

    public void delete(long id) {
        products.remove(id);
    }

}
