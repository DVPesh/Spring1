package ru.peshekhonov.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.peshekhonov.entities.Customer;
import ru.peshekhonov.entities.Item;
import ru.peshekhonov.entities.Product;
import ru.peshekhonov.repositories.CustomerRepository;
import ru.peshekhonov.repositories.ItemRepository;
import ru.peshekhonov.repositories.ProductRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class StoreService {

    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public List<Product> getProductListByCustomerId(long customerId) {
        return customerRepository.getItemsById(customerId)
                .stream()
                .map(Item::getProduct)
                .toList();
    }

    public Set<Customer> getCustomerListByProductId(long productId) {
        return new HashSet<>(productRepository.getItemsById(productId)
                .stream()
                .map(Item::getCustomer)
                .toList());
    }

    public List<TotalProductCost> getTotalProductCostListByCustomer(long customerId) {
        return customerRepository.getItemsById(customerId)
                .stream()
                .map(item -> {
                    long id = item.getProduct().getId();
                    String title = item.getProduct().getTitle();
                    BigDecimal cost = item.getCost().multiply(BigDecimal.valueOf(item.getQuantity()));
                    Timestamp timestamp = item.getTimestamp();
                    return new TotalProductCost(id, title, cost, timestamp);
                })
                .toList();
    }

    public void buy(long productId, long customerId, float quantity) {
        Product product = productRepository.findById(productId).orElseThrow();
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        itemRepository.save(new Item(product, customer, quantity, product.getCost()));
    }

    public void addProduct(String title, BigDecimal cost) {
        productRepository.save(new Product(title, cost));
    }

    public void addCustomer(String name) {
        customerRepository.save(new Customer(name));
    }
}
