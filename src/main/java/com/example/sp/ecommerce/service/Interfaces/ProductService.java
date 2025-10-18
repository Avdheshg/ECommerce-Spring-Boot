package com.example.sp.ecommerce.service.Interfaces;

import com.example.sp.ecommerce.model.Product;
import com.example.sp.ecommerce.payload.product.ProductDTO;
import com.example.sp.ecommerce.payload.product.ProductResponse;

import java.util.List;

public interface ProductService
{
    ProductResponse getAllProducts();
    ProductDTO getProductById(Long id);
    String addProduct(List<Product> products);
    ProductDTO updateProduct(ProductDTO productDTO, Long id);
    String removeProduct(Long id);

}
