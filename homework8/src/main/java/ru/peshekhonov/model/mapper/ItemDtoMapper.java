package ru.peshekhonov.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.peshekhonov.model.Item;
import ru.peshekhonov.model.dto.ItemDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = {ProductDtoMapper.class, CartDtoMapper.class})
public interface ItemDtoMapper {

    ItemDto map(Item item);

    Item map(ItemDto itemDto);
}
