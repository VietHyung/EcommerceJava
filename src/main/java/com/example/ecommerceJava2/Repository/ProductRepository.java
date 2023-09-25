package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query(value = "select * from products", nativeQuery = true)
	public List<Product> getProducts();

	@Query(value = "SELECT * FROM products WHERE category_id = ?1", nativeQuery = true)
	public List<Product> findByCategoryId(Long categoryId);
}