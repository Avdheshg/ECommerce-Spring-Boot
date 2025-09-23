package com.example.sp.ecommerce.service;

import java.util.List;
import java.util.*;

import com.example.sp.ecommerce.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService
{
    private List<Product> products = new ArrayList<>();
    private Long productId = 1L;

    @Override
    public List<Product> getAllProducts()
    {
        return products;
    }

    @Override
    public Product getProduct(Long id)
    {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.OK, "No product found for the given " + id));
    }

    @Override
    public String addProduct(List<Product> products)
    {
        for (Product product : products)
        {
            product.setId(getId());
            this.products.add(product);
        }
        return "Product(s) added successfully!";
    }

    @Override
    public String updateProduct(Product product, Long id)
    {
        Product foundProduct = products.stream()
                .filter(pro -> pro.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with the Id: " + id + " not found!"));

        foundProduct.setName(product.getName());
        return "Product with the Id: " + id + " updated successfully!";
    }

    @Override
    public String removeProduct(Long id) {
        Product foundProduct = products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with the Id: " + id + " not found!"));

        products.remove(foundProduct);
        return "Product with the Id: " + id + " successfully updated!";
    }

    private Long getId() { return productId++; }


}
