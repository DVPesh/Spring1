package ru.peshekhonov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.peshekhonov.exceptions.EntityNotFoundException;
import ru.peshekhonov.model.dto.QuantityItem;
import ru.peshekhonov.model.dto.VisitorDto;
import ru.peshekhonov.service.CartService;
import ru.peshekhonov.service.VisitorService;

import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final VisitorService visitorService;

    @PutMapping("/{id}")
    public String putProductToCustomerCart(@PathVariable long id, QuantityItem quantity, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        VisitorDto visitor = visitorService.findByUsername(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException("не удалось найти пользователя с именем " + principal.getName()));
        cartService.putProductToCart(id, quantity.getValue(), visitor);
        return "redirect:/product";
    }
}
