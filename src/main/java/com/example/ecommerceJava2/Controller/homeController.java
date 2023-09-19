package com.example.ecommerceJava2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class homeController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "hyun");
        return "home";
    }
}