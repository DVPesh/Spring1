package ru.peshekhonov.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ProductErrorReport {

    private int status;
    private String message;
    private Timestamp timestamp;
}
