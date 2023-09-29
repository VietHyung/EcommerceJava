package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.Category;
import com.example.ecommerceJava2.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(value = "select * from category", nativeQuery = true)
	public List<Category> getCategories();

	@Query(value = "SELECT * FROM category WHERE category_id = :categoryId", nativeQuery = true)
	Category findCategoryById(Long categoryId);

}
