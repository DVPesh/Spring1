package ru.peshekhonov.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.exceptions.ProductNotFoundException;
import ru.peshekhonov.model.dto.ProductDto;
import ru.peshekhonov.service.ProductService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> productList(@RequestParam(required = false) String titleFilter,
                                        @RequestParam(required = false) BigDecimal minCost,
                                        @RequestParam(required = false) BigDecimal maxCost,
                                        @RequestParam(required = false) Optional<Integer> page,
                                        @RequestParam(required = false) Optional<Integer> size,
                                        @RequestParam(required = false) Optional<String> sortField) {
        return productService.findAllByFilter(titleFilter, minCost, maxCost, page.orElse(1) - 1,
                size.orElse(3), sortField.filter(s -> !s.isBlank()).orElse("id")).stream().toList();
    }

    @GetMapping("/{id}")
    public ProductDto productForm(@PathVariable long id) {
        return productService.findProductById(id).orElseThrow(() -> new ProductNotFoundException("Товар не найден"));
    }

    @GetMapping("/new")
    public ProductDto addNewProduct() {
        return new ProductDto();
    }

    @DeleteMapping("{id}")
    public int deleteProductById(@PathVariable long id) {
        productService.deleteProductById(id);
        return HttpStatus.OK.value();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto addProduct(@RequestBody ProductDto product) {
        product.setId(null);
        return productService.save(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto updateProduct(@RequestBody ProductDto product) {
        return productService.save(product);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorReport> notFoundExceptionHandler(ProductNotFoundException e) {
        return new ResponseEntity<>(
                new ProductErrorReport(HttpStatus.NOT_FOUND.value(), e.getMessage(), Timestamp.from(Instant.now())),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorReport> conflictExceptionHandler(Exception e) {
        return new ResponseEntity<>(
                new ProductErrorReport(HttpStatus.CONFLICT.value(), e.getMessage(), Timestamp.from(Instant.now())),
                HttpStatus.CONFLICT);
    }
}
