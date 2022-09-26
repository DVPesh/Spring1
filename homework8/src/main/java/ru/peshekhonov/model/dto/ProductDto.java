package ru.peshekhonov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

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

    public ProductDto(String title, BigDecimal cost, LocalDate expirationDate, String supplierEmail, long quantity) {
        this.title = title;
        this.cost = cost;
        this.expirationDate = expirationDate;
        this.supplierEmail = supplierEmail;
        this.quantity = quantity;
    }
}
