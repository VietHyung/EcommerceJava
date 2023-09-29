package com.example.ecommerceJava2.Controller;

import com.example.ecommerceJava2.Model.Product;
import com.example.ecommerceJava2.Model.Result;
import com.example.ecommerceJava2.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ProductService productService;
    @GetMapping("/{categoryId}")
    public String getProductList(@PathVariable Long categoryId, Model m){
        List<Product> product = productService.getProductsByCategoryId(categoryId);

        m.addAttribute("products", product);
        return "productList";
    }
}
