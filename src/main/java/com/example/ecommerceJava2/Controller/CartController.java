package com.example.ecommerceJava2.Controller;

import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Service.CartService;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @PostMapping("/add/{pid}/{qty}")
    public String addProductToBasket(@PathVariable("pid") Long productId,
                                     @PathVariable("qty") Integer quantity,
                                     Principal principal,
                                     RedirectAttributes redirectAttributes) {
        if (principal == null) {
            redirectAttributes.addFlashAttribute("message", "You must login to add this product to your shopping basket");
            return "redirect:/login";
        }
        User user = userService.getUserByEmail(principal.getName());

        cartService.addProduct(productId, quantity, user);

        return "redirect:/";
    }
}
