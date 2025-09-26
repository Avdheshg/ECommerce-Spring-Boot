package com.example.sp.ecommerce.controller;

import com.example.sp.ecommerce.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

import com.example.sp.ecommerce.model.Product;

@RestController
@RequestMapping("/api/v1")
public class ProductController
{
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService)
    {
        this.productService = productService;
    }

    @GetMapping("/public/products")
    public ResponseEntity<List<Product>> getAllProducts()
    {
        List<Product> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/public/products/{productId}")
    public ResponseEntity<String> getProduct(@PathVariable Long productId)
    {
        try
        {
            Product foundProduct = productService.getProduct(productId);
            return new ResponseEntity<>(foundProduct.toString(), HttpStatus.OK);
        }
        catch (ResponseStatusException responseStatusException)
        {
            return new ResponseEntity<>(responseStatusException.getMessage(), responseStatusException.getStatusCode());
        }
    }

    @PostMapping("/public/products")
    public ResponseEntity<String> addProducts(@RequestBody List<Product> products)
    {
        String result = productService.addProduct(products);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping("/public/products/{productId}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable Long productId)
    {
        try {
            String result = productService.updateProduct(product, productId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ResponseStatusException responseStatusException) {
            return new ResponseEntity<>(responseStatusException.getMessage(), responseStatusException.getStatusCode());
        }
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId)
    {
        try {
            String result = productService.removeProduct(productId);
            System.out.println(result);
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException responseStatusException) {
            return new ResponseEntity<>(responseStatusException.getMessage(), responseStatusException.getStatusCode());
        }
    }

}
