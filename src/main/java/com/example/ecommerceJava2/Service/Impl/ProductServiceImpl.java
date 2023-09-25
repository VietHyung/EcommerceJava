package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.DTO.ProductDTO;
import com.example.ecommerceJava2.Model.Product;
import com.example.ecommerceJava2.Model.Result;
import com.example.ecommerceJava2.Repository.CategoryRepository;
import com.example.ecommerceJava2.Repository.ProductRepository;
import com.example.ecommerceJava2.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Result> getProductById(Long ProductId) {
        try {
            Product product = productRepository.findById(ProductId).orElseThrow(null);
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(product.getProductId());
            productDTO.setName(product.getName());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setCreatedAt(product.getCreatedAt());
            productDTO.setUpdatedAt(product.getUpdatedAt());
            productDTO.setCategoryName(String.valueOf(categoryRepository.findById(product.getCategory().getCategoryId()).orElseThrow()));
            return ResponseEntity.ok(new Result("SUCCESS", "OK", productDTO));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result("NOT FOUND PRODUCT", "NOT_FOUND", null));
        }
    }

    @Override
    public ResponseEntity<Result> getProductsByCategoryId(Long categoryId) {
        try {
            List<Product> products = productRepository.findByCategoryId(categoryId);
            List<ProductDTO> productDTOS = new ArrayList<>();
            for (Product product : products){
                ProductDTO productDTO = new ProductDTO();
                productDTO.setProductId(product.getProductId());
                productDTO.setName(product.getName());
                productDTO.setPrice(product.getPrice());
                productDTO.setDescription(product.getDescription());
                productDTO.setCreatedAt(product.getCreatedAt());
                productDTO.setUpdatedAt(product.getUpdatedAt());
                productDTO.setCategoryName(String.valueOf(categoryRepository.findById(product.getCategory().getCategoryId()).orElseThrow()));
                productDTOS.add(productDTO);
            }
            return ResponseEntity.ok(new Result("SUCCESS", "OK", productDTOS));
        } catch (NullPointerException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Result("NOT FOUND PRODUCT", "NOT_FOUND", null));
        }
    }
}
