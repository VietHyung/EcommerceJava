package com.example.ecommerceJava2.Repository;

import com.example.ecommerceJava2.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(value = "select * from category", nativeQuery = true)
	public List<Category> getCategories();


}
