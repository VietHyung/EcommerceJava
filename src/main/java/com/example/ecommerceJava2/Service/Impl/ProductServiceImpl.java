package com.example.ecommerceJava2.Service.Impl;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.DTO.ProductDTO;
import com.example.ecommerceJava2.Model.Product;
import com.example.ecommerceJava2.Model.Result;
import com.example.ecommerceJava2.Repository.CategoryRepository;
import com.example.ecommerceJava2.Repository.ProductRepository;
import com.example.ecommerceJava2.Service.CategoryService;
import com.example.ecommerceJava2.Service.ProductService;
import com.example.ecommerceJava2.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findProductById(productId);
    }

    @Override
    public List<Product> getProductsByCategoryId(Long categoryId) {
        try {
            return productRepository.findByCategoryId(categoryId);
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public void saveProduct(Product productDTO) {
        if (productDTO.getProductId() != null) {
            Product existingProduct = productRepository.findById(productDTO.getProductId()).orElse(null);
            if (existingProduct != null) {
                existingProduct.setName(productDTO.getName());
                existingProduct.setDescription(productDTO.getDescription());
                existingProduct.setProductImage(productDTO.getProductImage());
                existingProduct.setPrice(productDTO.getPrice());
                existingProduct.setQuantity(productDTO.getQuantity());

                existingProduct.setCategory(productDTO.getCategory());


                productRepository.save(existingProduct);
            } else {
                throw new NotFoundException("Product not found");
            }
        } else {
            Product newProduct = new Product();
            newProduct.setName(productDTO.getName());
            newProduct.setDescription(productDTO.getDescription());
            newProduct.setProductImage(productDTO.getProductImage());
            newProduct.setPrice(productDTO.getPrice());
            newProduct.setQuantity(productDTO.getQuantity());
            newProduct.setCategory(productDTO.getCategory());

            productRepository.save(newProduct);
        }
    }

    @Override
    public void deleteProduct(Long productId) throws ProductNotFoundException {
        Long countById = productRepository.countById(productId);
        if (countById == null || countById == 0) {
            throw new ProductNotFoundException("Couldn't find any product with ID " + productId);
        }
        productRepository.deleteById(productId);
    }

}
