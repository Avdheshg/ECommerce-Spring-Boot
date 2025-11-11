package com.example.sp.ecommerce.respositories;

import com.example.sp.ecommerce.model.Category;
import com.example.sp.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>
{

    Page<Product> findByCategory(Category category, Pageable pageable);

    Page<Product> findByNameIgnoreCaseLike(String name, Pageable pageable);

}
