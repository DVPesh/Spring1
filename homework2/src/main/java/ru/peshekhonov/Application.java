package ru.peshekhonov;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);
    private static int cartNumber;
    private static final List<Cart> carts = new ArrayList<>();

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductRepository productRepository = context.getBean(ProductRepository.class);

        while (true) {
            System.out.println("< ВЫБОР > 0-новая корзина, 1-список продуктов");
            System.out.println("2-добавить продукт в корзину, 3-убрать продукт из корзины");
            System.out.println("4-очистить корзину, 5-выбрать корзину, 6-удалить корзину");
            System.out.println("7-удалить все корзины, 8-показать содержимое всех корзин");
            try {
                switch (readValue()) {
                    case 0:
                        cartNumber = carts.size() == 0 ? 1 : carts.stream().mapToInt(Cart::getCartNumber).max().getAsInt() + 1;
                        Cart cart = context.getBean(Cart.class);
                        cart.setCartNumber(cartNumber);
                        carts.add(cart);
                        break;
                    case 1:
                        System.out.println(productRepository.getProducts());
                        break;
                    case 2:
                        System.out.println("id продукта?");
                        getCartByCartNumber().addProductById(readValue());
                        break;
                    case 3:
                        System.out.println("id продукта?");
                        getCartByCartNumber().removeProductById(readValue());
                        break;
                    case 4:
                        getCartByCartNumber().removeAll();
                        break;
                    case 5:
                        System.out.println("номер корзины?");
                        int value = readValue();
                        if (doCartExist(value)) {
                            cartNumber = value;
                        }
                        break;
                    case 6:
                        carts.removeIf(p -> p.getCartNumber() == cartNumber);
                        break;
                    case 7:
                        carts.clear();
                        break;
                    case 8:
                        carts.stream().filter(p -> p.getCartNumber() != cartNumber)
                                .forEach(p -> System.out.printf("корзина: %s %s%n", p.getCartNumber(), p.getProducts()));
                }
                if (carts.size() > 0 && !doCartExist(cartNumber)) {
                    cartNumber = carts.stream().mapToInt(Cart::getCartNumber).min().getAsInt();
                }
                if (carts.size() > 0) {
                    System.out.printf("текущая корзина: %s %s%n", cartNumber, getCartByCartNumber().getProducts());
                }
                System.out.println("количество корзин: " + carts.size());
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int readValue() {
        if (!scanner.hasNextInt()) {
            System.exit(0);
        }
        return scanner.nextInt();
    }

    private static boolean doCartExist(int cartNumber) {
        return carts.stream().mapToInt(Cart::getCartNumber).anyMatch(number -> number == cartNumber);
    }

    private static Cart getCartByCartNumber() {
        return carts.stream().filter(cart -> cart.getCartNumber() == cartNumber)
                .findFirst().orElseThrow(() -> new NoSuchElementException("нет корзины " + (carts.size() > 0 ? cartNumber : "")));
    }
}
