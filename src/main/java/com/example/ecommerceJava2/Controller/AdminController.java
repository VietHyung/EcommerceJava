package com.example.ecommerceJava2.Controller;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.DTO.CategoryDTO;
import com.example.ecommerceJava2.Model.DTO.ProductDTO;
import com.example.ecommerceJava2.Model.Product;
import com.example.ecommerceJava2.Service.CategoryService;
import com.example.ecommerceJava2.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/products/new")
    public String addProduct(Model model) {
        ProductDTO product = new ProductDTO();
        model.addAttribute("product", product);
        return "admin/product/addProduct";
    }

    @PostMapping("/products/save")
    public String saveProduct(ProductDTO product, RedirectAttributes redirect) {
        productService.saveProduct(product);
        redirect.addFlashAttribute("message", "The product was saved successfully");
        return "redirect:/";
    }

    @GetMapping("/category/new")
    public String addCategory(Model model) {
        CategoryDTO category = new CategoryDTO();
        model.addAttribute("category", category);
        return "admin/category/addCategory";
    }

    @PostMapping("/category/save")
    public String saveCategory(Category category, RedirectAttributes redirect) {
        categoryService.saveCategory(category);
        redirect.addFlashAttribute("message", "The category was saved successfully");
        return "redirect:/";
    }
}
