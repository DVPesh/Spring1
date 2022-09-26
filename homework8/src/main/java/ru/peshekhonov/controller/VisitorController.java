package ru.peshekhonov.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.peshekhonov.exceptions.EntityNotFoundException;
import ru.peshekhonov.model.dto.VisitorDto;
import ru.peshekhonov.service.RoleService;
import ru.peshekhonov.service.VisitorService;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/visitor")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;
    private final RoleService roleService;

    @GetMapping
    public String visitorList(@RequestParam(required = false) String usernameFilter,
                              @RequestParam(required = false) String role,
                              @RequestParam(required = false) Optional<Integer> page,
                              @RequestParam(required = false) Optional<Integer> size,
                              @RequestParam(required = false) Optional<String> sortField,
                              Model model) {
        model.addAttribute("visitors", visitorService.findAllByFilter(usernameFilter, role,
                page.orElse(1) - 1, size.orElse(3), sortField.filter(s -> !s.isBlank()).orElse("username")));
        model.addAttribute("roles", roleService.findAllNames());
        return "visitors";
    }

    @GetMapping("/{id}")
    public String visitorForm(@PathVariable long id, Model model) {
        model.addAttribute("visitor", visitorService.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь не найден")));
        model.addAttribute("roles", roleService.findAllAndAddNull());
        return "visitor_form";
    }

    @GetMapping("/new")
    public String addNewVisitor(Model model) {
        model.addAttribute("visitor", new VisitorDto());
        model.addAttribute("roles", roleService.findAllAndAddNull());
        return "visitor_form";
    }

    @DeleteMapping("/{id}")
    public String deleteVisitorById(@PathVariable long id) {
        visitorService.deleteById(id);
        return "redirect:/visitor";
    }

    @PostMapping
    public String storeVisitor(@Valid @ModelAttribute("visitor") VisitorDto visitor, BindingResult bindingResult, Model model) {
        model.addAttribute("roles", roleService.findAllAndAddNull());
        if (bindingResult.hasErrors()) {
            return "visitor_form";
        }
        if (!visitor.getPassword().equals(visitor.getMatchingPassword())) {
            bindingResult.rejectValue("matchingPassword", "error.password", "не совпадает с паролем");
            return "visitor_form";
        }
        visitorService.save(visitor);
        return "redirect:/visitor";
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
