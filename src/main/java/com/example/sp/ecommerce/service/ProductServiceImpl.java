package com.example.sp.ecommerce.service;

import java.util.List;

import com.example.sp.ecommerce.model.Product;
import com.example.sp.ecommerce.respositories.ProductRepository;
import com.example.sp.ecommerce.service.Interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id)
    {
        List<Product> products = getAllProducts();

        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.OK, "No product found for the given " + id));
    }

    @Override
    public String addProduct(List<Product> products)
    {
        productRepository.saveAll(products);

        return "Product(s) added successfully!";
    }

    @Override
    public String updateProduct(Product product, Long id)
    {
        List<Product> products = productRepository.findAll();

        Product foundProduct = products.stream()
                .filter(pro -> pro.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with the Id: " + id + " not found!"));

        foundProduct.setName(product.getName());
        productRepository.save(foundProduct);

        return "Product with the Id: " + id + " updated successfully!";
    }

    @Override
    public String removeProduct(Long id)
    {
        List<Product> products = productRepository.findAll();

        Product foundProduct = products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with the Id: " + id + " not found!"));

        productRepository.delete(foundProduct);
        return "Product with the Id: " + id + " successfully updated!";
    }



}
