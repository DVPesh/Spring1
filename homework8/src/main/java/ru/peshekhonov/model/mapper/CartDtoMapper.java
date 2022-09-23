package ru.peshekhonov.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import ru.peshekhonov.model.Cart;
import ru.peshekhonov.model.dto.CartDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CartDtoMapper {

    CartDto map(Cart cart);

    @Mapping(target = "items", ignore = true)
    Cart map(CartDto cartDto);
}
