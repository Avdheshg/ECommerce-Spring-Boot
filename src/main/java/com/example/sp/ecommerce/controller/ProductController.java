package com.example.sp.ecommerce.controller;

import com.example.sp.ecommerce.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    @PostMapping("/public/products")
    public ResponseEntity<String> addProducts(@Valid @RequestBody List<Product> products)
    {
        System.out.println("Inside POST of MULTIPLE products");
        String result = productService.addProduct(products);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/public/product")
    public ResponseEntity<String> addProducts(@Valid @RequestBody Product product)
    {
        System.out.println("Inside POST of products");
        List<Product> products = new ArrayList<>();
        products.add(product);
        String result = productService.addProduct(products);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
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
        Product foundProduct = productService.getProductById(productId);
        return new ResponseEntity<>(foundProduct.toString(), HttpStatus.OK);
    }

    @PatchMapping("/public/products/{productId}")
    public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable Long productId)
    {
        String result = productService.updateProduct(product, productId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId)
    {
        String result = productService.removeProduct(productId);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

}
