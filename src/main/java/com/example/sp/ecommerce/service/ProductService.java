package com.example.sp.ecommerce.service;

import java.util.List;

import com.example.sp.ecommerce.model.Product;

public interface ProductService
{
    List<Product> getAllProducts();
    Product getProduct(Long id);

    String addProduct(List<Product> product);
    String updateProduct(Product product, Long id);

    String removeProduct(Long id);

}
