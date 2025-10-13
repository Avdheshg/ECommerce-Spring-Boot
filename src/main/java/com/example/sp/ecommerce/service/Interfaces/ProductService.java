package com.example.sp.ecommerce.service.Interfaces;

import com.example.sp.ecommerce.model.Product;

import java.util.List;

public interface ProductService
{
    List<Product> getAllProducts();
    Product getProductById(Long id);
    String addProduct(List<Product> products);
    String updateProduct(Product product, Long id);
    String removeProduct(Long id);

}
