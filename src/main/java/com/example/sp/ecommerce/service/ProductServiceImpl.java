package com.example.sp.ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.example.sp.ecommerce.exceptions.ResourceNotFoundException;
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
        List<Product> products = productRepository.findAll();

        if (products.isEmpty())
        {
            throw new ResourceNotFoundException("No Products present!!");
        }

        return products;
    }

    @Override
    public Product getProductById(Long productId)
    {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        return optionalProduct
                .orElseThrow(() -> new ResourceNotFoundException("Product", "Id", productId));
    }

    @Override
    public String addProduct(List<Product> products)
    {
        productRepository.saveAll(products);

        return "Product(s) added successfully!";
    }

    @Override
    public String updateProduct(Product product, Long productId)
    {
        Product foundProduct = getProductById(productId);

        foundProduct.setName(product.getName());
        productRepository.save(foundProduct);

        return "Product with the Id: " + productId + " updated successfully!";
    }

    @Override
    public String removeProduct(Long productId)
    {
        Product foundProduct = getProductById(productId);

        productRepository.delete(foundProduct);

        return "Product with the Id: " + productId + " successfully deleted!";
    }

}
