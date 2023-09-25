package com.example.ecommerceJava2.Controller;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.DTO.CategoryDTO;
import com.example.ecommerceJava2.Model.Product;
import com.example.ecommerceJava2.Model.Role;
import com.example.ecommerceJava2.Model.User;
import com.example.ecommerceJava2.Repository.CategoryRepository;
import com.example.ecommerceJava2.Repository.ProductRepository;
import com.example.ecommerceJava2.Repository.UserRepository;
import com.example.ecommerceJava2.Service.CategoryService;
import com.example.ecommerceJava2.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;


@Controller
public class homeController {
    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductRepository productRepo;

    @ModelAttribute
    public void commonUser(Principal p, Model m) {
        if (p != null) {
            String email = p.getName();
            User user = userRepo.findByEmail(email);
            m.addAttribute("user", user);
        }
    }

    @GetMapping("/")
    public String index(Model m) {

        List<Product> products = this.productRepo.getProducts();
        List<CategoryDTO> categories = this.categoryService.getAllCategories();
        m.addAttribute("categories", categories);
        m.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(Model m) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof AnonymousAuthenticationToken)) {

            User user = this.userRepo.loadUserByUserName(auth.getName());

            if (user.getRole().equals(Role.CUSTOMER)) {
                return "redirect:/customer/home";
            }
            if (user.getRole().equals(Role.ADMIN)) {
                return "redirect:/admin/home";
            }

        }
        m.addAttribute("title", "Login | StoreWall");
        return "login";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session, Model m, HttpServletRequest request) {

        String url = request.getRequestURL().toString();
        url = url.replace(request.getServletPath(), "");

        User u = userService.saveUser(user, url);

        if (u != null) { // System.out.println("save sucess");
            session.setAttribute("msg", "Register successfully");

        } else { // System.out.println("error in server");
            session.setAttribute("msg", "Something wrong server");
        }

        return "redirect:/register";
    }

    @GetMapping("/verify")
    public String verifyAccount(@Param("code") String code, Model m) {
        boolean f = userService.verifyAccount(code);

        if (f) {
            m.addAttribute("msg", "Sucessfully your account is verified");
        } else {
            m.addAttribute("msg", "may be your vefication code is incorrect or already veified ");
        }

        return "message";
    }


}
