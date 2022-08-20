package ru.peshekhonov.products;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProductException extends Exception {

    private Product product;
}
