package ru.peshekhonov.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class TotalProductCost {

    private long id;
    private String title;
    private BigDecimal cost;
    private Timestamp timestamp;
}
