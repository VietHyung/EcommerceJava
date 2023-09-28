package com.example.ecommerceJava2.Controller;

import com.example.ecommerceJava2.Model.*;
import com.example.ecommerceJava2.Repository.OrderItemRepository;
import com.example.ecommerceJava2.Service.CartItemService;
import com.example.ecommerceJava2.Service.CartService;
import com.example.ecommerceJava2.Service.OrderService;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.webjars.NotFoundException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @GetMapping("/payment")
    public String createOrders(Model model, Principal principal) {
        if (principal != null) {
            User user = userService.getUserByEmail(principal.getName());
            Cart customerCart = cartService.findCartByUserId(user.getUserId());
            List<CartItem> orderBaskets = cartItemService.findCartItemByCartId(customerCart.getCartId());

            model.addAttribute("order", new Order());
            model.addAttribute("user", user);
            model.addAttribute("orderBaskets", orderBaskets);
        } else {
            model.addAttribute("error", new NotFoundException("Orders for payment was not found"));
            return "/error/404";
        }
        return "checkout";
    }

    @PostMapping("/payment")
    public String saveOrder(Order newOrder, Principal principal,
                            Model model, RedirectAttributes attributes) {
        User user = userService.getUserByEmail(principal.getName());
        Cart customerCart = cartService.findCartByUserId(user.getUserId());
        List<CartItem> orderBaskets = cartItemService.findCartItemByCartId(customerCart.getCartId());
        newOrder.setUser(user);
        newOrder.setTotalPrice(orderService.countSum(orderBaskets));
        newOrder.setStatus(OrderStatus.PENDING);
        try {
            orderService.saveOrder(newOrder);
            attributes.addFlashAttribute("message", "Order was completed! Check your email!");
//            sendVerificationEmail(newOrder);
        } catch (JpaSystemException ex){// | MessagingException | UnsupportedEncodingException ex) {
            model.addAttribute("error", ex.getCause().getCause().getMessage());
            return "error/404";
        }
        return "redirect:/";
    }

    private void saveOrderItem(Order newOrder, List<CartItem> orderBaskets) {
        for (CartItem basketItem : orderBaskets) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(newOrder);
            orderItem.setProduct(basketItem.getProduct());
            orderItem.setQuantity(basketItem.getQuantity());

            orderItemRepository.save(orderItem);
        }
    }


    private void sendVerificationEmail(Order order)
            throws MessagingException, UnsupportedEncodingException {

        String shipping = "Direct";

        String subject = "Thank you for ordering";
        String senderName = "Store";
        String mailContent = "<p><b>Order number:</b> " + order.getId() + "</p>";
        mailContent += "<p><b>Payment:</b> " + order.getStatus() + "</p>";
        mailContent += "<p><b>Shipping:</b> " + shipping + "</p>";
        mailContent += "<p><b>City:</b> " + order.getCity() + "</p>";
        mailContent += "<p><b>Address:</b> " + order.getAddress() + "</p>";
        mailContent += "<p><b>Order total:</b> " + order.getTotalPrice() + "</p>";

        mailContent += "<hr><img src='cid:logoImage' width=150 />";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("levieth4869@gmail.com", senderName);
        helper.setTo(order.getUser().getEmail());
        helper.setSubject(subject);
        helper.setText(mailContent, true);

        ClassPathResource pathResource = new ClassPathResource("/static/assets/email.gif");
        helper.addInline("logoImage", pathResource);
        javaMailSender.send(message);
    }
}
