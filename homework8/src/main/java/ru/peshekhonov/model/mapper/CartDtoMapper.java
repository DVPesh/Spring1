package ru.peshekhonov.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.peshekhonov.model.Cart;
import ru.peshekhonov.model.dto.CartDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {VisitorDtoMapper.class})
public interface CartDtoMapper {

    CartDto map(Cart cart);

    Cart map(CartDto cartDto);
}
