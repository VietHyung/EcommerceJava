package com.example.ecommerceJava2.Controller;

import com.example.ecommerceJava2.Model.Cart;
import com.example.ecommerceJava2.Model.CartItem;
import com.example.ecommerceJava2.Model.Order;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Repository.CartItemRepository;
import com.example.ecommerceJava2.Service.CartItemService;
import com.example.ecommerceJava2.Service.CartService;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/myCart")
    public String showShoppingCart(Model model,
                                   Principal principal) {
        if (principal != null) {
            User customer = userService.getUserByEmail(principal.getName());
            Cart customerCart = cartService.findCartByUserId(customer.getUserId());
            List<CartItem> cartItems = cartItemService.findCartItemByCartId(customerCart.getCartId());

            model.addAttribute("cartInfo", customerCart);
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("order", new Order());
        }
//        else {
//            model.addAttribute("error", new NotFoundException("Order basket was not found"));
//            return "/error/404";
//        }
        return "shopping-cart";
    }

    @PostMapping("/add/{pid}/{qty}")
    public String addProductToCart(@PathVariable("pid") Long productId,
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

    @PostMapping("/update/{ctid}/{qty}")
    public String updateQuantity(   @PathVariable("ctid") Long cartItemId,
                                    @PathVariable("qty") Integer quantity,
                                    Principal principal,
                                    RedirectAttributes redirectAttributes) {
        if (principal == null) {
            redirectAttributes.addFlashAttribute("message", "You must login to add this product to your shopping basket");
            return "redirect:/login";
        }
        User user = userService.getUserByEmail(principal.getName());
        CartItem cartItem = cartItemService.findCartItemById(cartItemId);

        cartService.updateQuantity(quantity, cartItem);

        return "redirect:/cart/myCart";
    }

    @PostMapping("/remove/{ctid}")
    public String removeProductFromCart(@PathVariable("ctid") Long cartItemId,
                                        Principal principal,
                                        RedirectAttributes redirectAttributes) {
        if (principal == null) {
            redirectAttributes.addFlashAttribute("message", "You must login to remove this product from your shopping basket");
            return "redirect:/login";
        }

        cartService.removeCartProduct(cartItemId);

        return "redirect:/cart/myCart";
    }

}
