package ru.peshekhonov.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.peshekhonov.model.Item;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    private Long id;
    private VisitorDto visitor;
    private Set<Item> items;

    public CartDto(VisitorDto visitor) {
        this.visitor = visitor;
    }
}
