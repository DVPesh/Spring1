package ru.peshekhonov.products;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    private long id;
    private String title;
    private BigDecimal cost;
}
