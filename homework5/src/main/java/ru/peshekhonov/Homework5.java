package ru.peshekhonov;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.Configuration;
import ru.peshekhonov.products.Product;
import ru.peshekhonov.products.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class Homework5 {

    private static ProductRepository repository;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = new Configuration()
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        repository = new ProductRepository(entityManagerFactory);

        repository.save(new Product("Бананы", new BigDecimal(95), LocalDate.of(2022, Month.SEPTEMBER, 10), "supplier1@mail.ru", 20));
        repository.save(new Product("Арбуз", new BigDecimal(25), LocalDate.of(2022, Month.SEPTEMBER, 20), "supplier2@mail.ru", 19));
        repository.save(new Product("Дыня", new BigDecimal(86.6), LocalDate.of(2022, Month.SEPTEMBER, 22), "supplier3@mail.ru", 23));
        repository.save(new Product("Слива", new BigDecimal(69.5), LocalDate.of(2022, Month.SEPTEMBER, 25), "supplier4@mail.ru", 57));
        repository.save(new Product("Тыква", new BigDecimal(75), LocalDate.of(2022, Month.SEPTEMBER, 28), "supplier5@mail.ru", 51));

        repository.findAll().forEach(System.out::println);

        changeQuantity(5, 10);
        changeQuantity(1, 45);

        repository.findAll().forEach(System.out::println);

        repository.deleteById(2);
        repository.deleteById(4);

        repository.findAll().forEach(System.out::println);

        repository.save(new Product("Бананы", new BigDecimal(95), LocalDate.of(2022, Month.SEPTEMBER, 10), "supplier1@mail.ru", 20));
        repository.save(new Product("Арбуз", new BigDecimal(25), LocalDate.of(2022, Month.SEPTEMBER, 20), "supplier2@mail.ru", 67));

        repository.findAll().forEach(System.out::println);
    }

    private static void changeQuantity(long id, long quantity) {
        repository.findById(id).ifPresent(value -> {
            value.setQuantity(quantity);
            repository.save(value);
        });
    }
}
