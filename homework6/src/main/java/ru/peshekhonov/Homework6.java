package ru.peshekhonov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.peshekhonov.config.AppConfig;
import ru.peshekhonov.services.StoreService;

import java.math.BigDecimal;

public class Homework6 {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StoreService service = context.getBean(StoreService.class);

        service.addProduct("Морковь", BigDecimal.valueOf(40.00));
        service.addCustomer("Ольга");

        long productId = 4, customerId = 2;
        service.buy(productId, customerId, 7.4f);

        System.out.printf("список покупателей товара с id=%d%n", productId);
        System.out.println(service.getCustomerListByProductId(productId));
        System.out.printf("список товаров покупателя с id=%d%n", customerId);
        System.out.println(service.getProductListByCustomerId(customerId));
        System.out.printf("список товаров с указанием общей стоимости товара на момент покупки для покупателя c id=%d%n", customerId);
        System.out.println(service.getTotalProductCostListByCustomer(customerId));
    }
}
