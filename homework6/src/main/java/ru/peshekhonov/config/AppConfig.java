package ru.peshekhonov.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.peshekhonov.entities.Customer;
import ru.peshekhonov.entities.Item;
import ru.peshekhonov.entities.Product;

@Configuration
@ComponentScan("ru.peshekhonov")
public class AppConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Item.class)
                .buildSessionFactory();
    }
}
