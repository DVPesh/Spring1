package ru.peshekhonov.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peshekhonov.model.Visitor;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class RoleDto {

    private Long id;

    private String name;

    private Set<Visitor> visitors;
}
