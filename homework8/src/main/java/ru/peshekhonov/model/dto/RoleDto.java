package ru.peshekhonov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peshekhonov.model.Visitor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    @NotNull(message = "поле не может быть пустым")
    @Pattern(regexp = "ROLE_[a-zA-Z]\\w*", message = "неверно указана роль")
    private String name;

    private Set<Visitor> visitors;

    public RoleDto(String name) {
        this.name = name;
    }
}
