package com.example.sp.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.sp.ecommerce.exceptions.ResourceNotFoundException;
import com.example.sp.ecommerce.model.Product;
import com.example.sp.ecommerce.payload.product.ProductDTO;
import com.example.sp.ecommerce.payload.product.ProductResponse;
import com.example.sp.ecommerce.respositories.ProductRepository;
import com.example.sp.ecommerce.service.Interfaces.ProductService;
import com.example.sp.ecommerce.util.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    private final List<String> allowedFieldsForSorting = List.of("id", "name", "amount", "stockQuantity");

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir)
    {
        Sort sort = SortUtils.getValidSort(sortBy, sortDir, allowedFieldsForSorting);

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sort);
        Page<Product> productPage =productRepository.findAll(pageDetails);

        List<Product> products = productPage.getContent();

        if (products.isEmpty())
        {
            throw new ResourceNotFoundException("No Products present!!");
        }

        return getProductResponseFromProducts(products, productPage);
    }

    @Override
    public ProductDTO getProductById(Long productId)
    {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent())
        {
            return new ProductDTO(optionalProduct.get());
        }
        else
        {
            throw new ResourceNotFoundException("Product", "Id", productId);
        }
    }

    @Override
    public String addProduct(List<Product> products)
    {
        productRepository.saveAll(products);

        return "Products added successfully!";
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, Long productId)
    {
        ProductDTO foundProductDTO = getProductById(productId);

        foundProductDTO.setName(productDTO.getName());
        productRepository.save(getProductFromDTO(foundProductDTO));

        return foundProductDTO;
    }

    @Override
    public String removeProduct(Long productId)
    {
        productRepository.deleteById(productId);

        return "Product with the Id: " + productId + " successfully deleted!";
    }

    public Product getProductFromDTO(ProductDTO productDTO)
    {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getAmount(), productDTO.getCategoryId(), productDTO.getStockQuantity());
    }

    public ProductResponse getProductResponseFromProducts(List<Product> products, Page<Product> productPage)
    {
        List<ProductDTO> productsDTOs = new ArrayList<>();

        productsDTOs = products.stream()
                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getAmount(), product.getCategoryId(), product.getStockQuantity()))
                .toList();

        return new ProductResponse(productPage.getTotalElements(), productPage.getNumber(), productPage.getSize(), productPage.getTotalPages(), productPage.isLast(), productsDTOs);

    }

}
