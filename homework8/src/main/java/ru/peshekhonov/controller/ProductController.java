package ru.peshekhonov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.exceptions.EntityNotFoundException;
import ru.peshekhonov.model.dto.ProductDto;
import ru.peshekhonov.model.dto.QuantityItem;
import ru.peshekhonov.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public String productList(@RequestParam(required = false) String titleFilter,
                              @RequestParam(required = false) BigDecimal minCost,
                              @RequestParam(required = false) BigDecimal maxCost,
                              @RequestParam(required = false) Optional<Integer> page,
                              @RequestParam(required = false) Optional<Integer> size,
                              @RequestParam(required = false) Optional<String> sortField,
                              Model model) {
        model.addAttribute("products", productService.findAllByFilter(titleFilter, minCost, maxCost,
                page.orElse(1) - 1, size.orElse(3), sortField.filter(s -> !s.isBlank()).orElse("id")));
        model.addAttribute("quantity", new QuantityItem());
        return "products";
    }

    @GetMapping("/{id}")
    public String productForm(@PathVariable long id, Model model) {
        model.addAttribute("product", productService.findProductById(id)
                .orElseThrow(() -> new EntityNotFoundException("Товар не найден")));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable long id) {
        productService.deleteProductById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String storeProduct(@Valid @ModelAttribute("product") ProductDto product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            checkForFieldTypeMismatchError(bindingResult, "cost");
            checkForFieldTypeMismatchError(bindingResult, "expirationDate");
            checkForFieldTypeMismatchError(bindingResult, "quantity");
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    private static void checkForFieldTypeMismatchError(BindingResult bindingResult, String fieldName) {
        FieldError error = bindingResult.getFieldError(fieldName);
        if (error != null && error.isBindingFailure()) {
            bindingResult.rejectValue(fieldName, error.getCode(), "неверный формат");
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundExceptionHandler(Model model, EntityNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictExceptionHandler(Model model, Exception e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
