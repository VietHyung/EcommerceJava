package com.example.ecommerceJava2.Controller;

import com.example.ecommerceJava2.Model.Cart;
import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Model.Order;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Service.CartItemService;
import com.example.ecommerceJava2.Service.CartService;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.webjars.NotFoundException;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/payment")
    public String createOrders(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getUserByEmail(principal.getName());
            Cart customerCart = cartService.findCartByUserId(user.getUserId());
            List<CartItem> orderBaskets = cartItemService.findCartItemByCartId(customerCart.getCartId());

            model.addAttribute("order", new Order());
            model.addAttribute("user", user);
            model.addAttribute("orderBaskets", orderBaskets);
//            model.addAttribute("waiting", OrderType.Ожидание);
//            model.addAttribute("payed", OrderType.Оплачено);
        } else {
            model.addAttribute("error", new NotFoundException("Orders for payment was not found"));
            return "/error/404";
        }
        return "checkout";
    }
}
