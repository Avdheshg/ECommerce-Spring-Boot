package com.example.sp.ecommerce.service.Interfaces;

import com.example.sp.ecommerce.payload.product.ProductDTO;
import com.example.sp.ecommerce.payload.product.ProductResponse;

import java.util.List;

public interface ProductService
{
    String addProducts(List<ProductDTO> productDTOs);
    String addProduct(ProductDTO productDTO);
    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    ProductResponse getProductsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
//    ProductDTO getProductById(Long id);
//    ProductDTO updateProduct(ProductDTO productDTO, Long id);
//    String removeProduct(Long id);

}
