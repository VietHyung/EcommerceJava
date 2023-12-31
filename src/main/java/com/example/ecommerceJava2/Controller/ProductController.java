package com.example.ecommerceJava2.Controller;

import com.example.ecommerceJava2.Model.DTO.CategoryDTO;
import com.example.ecommerceJava2.Model.DTO.ProductDTO;
import com.example.ecommerceJava2.Model.Product;
import com.example.ecommerceJava2.Model.Result;
import com.example.ecommerceJava2.Repository.CategoryRepository;
import com.example.ecommerceJava2.Repository.ProductRepository;
import com.example.ecommerceJava2.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductService productService;

    @GetMapping("/detail/{productId}")
    public String getProductDetail(@PathVariable Long productId,Model m){//@PathVariable Long productId, Model model) {
        Product product = productService.getProductById(productId);
        m.addAttribute("product", product);
        return "productDetail";
    }
}
