package ru.peshekhonov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.peshekhonov.model.dto.ProductDto;
import ru.peshekhonov.model.mapper.ProductDtoMapper;
import ru.peshekhonov.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductDtoMapper mapper;
    private final ProductRepository productRepository;

    public Page<ProductDto> findAllByFilter(String title, BigDecimal min, BigDecimal max, int page, int size, String sortField) {
        title = title == null || title.isBlank() ? null : "%" + title.trim() + "%";
        return productRepository.productsByFilter(title, min, max, PageRequest.of(page, size, Sort.by(sortField))).map(mapper::map);
    }

    public Optional<ProductDto> findProductById(Long id) {
        return productRepository.findById(id).map(mapper::map);
    }

    public void save(ProductDto productDto) {
        productRepository.save(mapper.map(productDto));
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
