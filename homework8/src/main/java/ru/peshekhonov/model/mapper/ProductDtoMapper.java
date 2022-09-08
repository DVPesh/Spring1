package ru.peshekhonov.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.peshekhonov.model.Product;
import ru.peshekhonov.model.dto.ProductDto;

@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductDtoMapper {

    ProductDto map(Product product);

    Product map(ProductDto productDto);
}
