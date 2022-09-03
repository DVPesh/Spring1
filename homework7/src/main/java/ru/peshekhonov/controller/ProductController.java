package ru.peshekhonov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.exceptions.ProductNotFoundException;
import ru.peshekhonov.products.Product;
import ru.peshekhonov.products.ProductRepository;

import javax.validation.Valid;
import java.sql.SQLException;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping
    public String productList(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "products";
    }

    @GetMapping("/{id}")
    public String productForm(@PathVariable long id, Model model) {
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Товар не найден")));
        return "product_form";
    }

    @GetMapping("/new")
    public String addNewProduct(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable long id) {
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @PostMapping
    public String storeProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            checkForFieldTypeMismatchError(bindingResult, "cost");
            checkForFieldTypeMismatchError(bindingResult, "expirationDate");
            checkForFieldTypeMismatchError(bindingResult, "quantity");
            return "product_form";
        }
        productRepository.save(product);
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
    public String notFoundExceptionHandler(Model model, ProductNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String conflictExceptionHandler(Model model, SQLException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
