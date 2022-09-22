package ru.peshekhonov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VisitorDto {

    private Long id;
    private String username;
    private String phoneNumber;
    private String password;
}
