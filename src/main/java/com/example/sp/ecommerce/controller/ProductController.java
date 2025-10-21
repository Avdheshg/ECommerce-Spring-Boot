package com.example.sp.ecommerce.controller;

import com.example.sp.ecommerce.config.AppConstants;
import com.example.sp.ecommerce.payload.product.ProductDTO;
import com.example.sp.ecommerce.payload.product.ProductResponse;
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
    public ResponseEntity<String> addProduct(@Valid @RequestBody Product product)
    {
        List<Product> products = new ArrayList<>();
        products.add(product);
        String result = productService.addProduct(products);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
    {
        ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long productId)
    {
        ProductDTO foundProductDTO = productService.getProductById(productId);
        return new ResponseEntity<>(foundProductDTO, HttpStatus.OK);
    }

    @PutMapping("/public/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO product, @PathVariable Long productId)
    {
        ProductDTO productDTO = productService.updateProduct(product, productId);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId)
    {
        String result = productService.removeProduct(productId);
        return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
    }

}
