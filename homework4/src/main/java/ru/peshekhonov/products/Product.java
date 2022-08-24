package ru.peshekhonov.products;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    private long id;
    @NotBlank(message = "значение не может быть пустым")
    private String title;
    @Positive(message = "значение должно быть больше нуля")
    @NotNull(message = "значение должно быть задано")
    @Digits(integer = 6, fraction = 2, message = "неверное значение")
    private BigDecimal cost;
    @Future(message = "товар с истёкшим сроком годности")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate expirationDate;
    @Email(message = "неверный Email")
    private String supplierEmail;
    @Min(value = 1, message = "товар должен быть в наличии")
    private long quantity;
}
