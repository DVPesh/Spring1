package ru.peshekhonov.products;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@AllArgsConstructor
@ToString
public class Product {

    @Getter
    private long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private BigDecimal cost;
}
