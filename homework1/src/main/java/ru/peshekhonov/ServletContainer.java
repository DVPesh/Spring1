package ru.peshekhonov;

import ru.peshekhonov.products.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class ServletContainer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();
        productRepository.addProduct("Пароварка", new BigDecimal(7500));
        productRepository.addProduct("Мясорубка", new BigDecimal(5500));
        productRepository.addProduct("Йогурница", new BigDecimal(2300));
        productRepository.addProduct("Аэрогриль", new BigDecimal(6700));
        productRepository.addProduct("Холодильник", new BigDecimal(94000));
        productRepository.addProduct("Говядина", new BigDecimal(820));
        productRepository.addProduct("Курица", new BigDecimal(300));
        productRepository.addProduct("Масло сливочное", new BigDecimal(220));
        productRepository.addProduct("Хлеб", new BigDecimal(55));
        productRepository.addProduct("Арбуз", new BigDecimal(20));
        productRepository.addProduct("Дыня", new BigDecimal(100));
        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
