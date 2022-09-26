package ru.peshekhonov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BaseController {

    @GetMapping
    public String mainPage() {
        return "redirect:/product";
    }

    @GetMapping("/access_denied")
    public String accessDeniedPage() {
        return "access_denied";
    }
}