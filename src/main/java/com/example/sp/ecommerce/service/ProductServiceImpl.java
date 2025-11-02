package com.example.sp.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.sp.ecommerce.exceptions.ResourceNotFoundException;
import com.example.sp.ecommerce.model.Category;
import com.example.sp.ecommerce.model.Product;
import com.example.sp.ecommerce.payload.product.ProductDTO;
import com.example.sp.ecommerce.payload.product.ProductResponse;
import com.example.sp.ecommerce.respositories.CategoryRepository;
import com.example.sp.ecommerce.respositories.ProductRepository;
import com.example.sp.ecommerce.service.Interfaces.ProductService;
import com.example.sp.ecommerce.util.SortUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private final List<String> allowedFieldsForSorting = List.of("id", "name", "amount", "stockQuantity");

    @Override
    public String addProducts(List<ProductDTO> productDTOs)
    {
        productRepository.saveAll(getProductsFromDTOs(productDTOs));

        return "Products added successfully!";
    }

    @Override
    public String addProduct(ProductDTO productDTO)
    {
        productRepository.save(developProduct(productDTO));

        return "Product added successfully!";
    }

    // Made the above endpoint working
    // Need to make the List<Product> endpoint working ie adding multiple products working with ProductDTOs so that we can send the product ids with each Product inside the list

//
//    @Override
//    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir)
//    {
//        Sort sort = SortUtils.getValidSort(sortBy, sortDir, allowedFieldsForSorting);
//
//        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sort);
//        Page<Product> productPage =productRepository.findAll(pageDetails);
//
//        List<Product> products = productPage.getContent();
//
//        if (products.isEmpty())
//        {
//            throw new ResourceNotFoundException("No Products present!!");
//        }
//
//        return getProductResponseFromProducts(products, productPage);
//    }
//
//    @Override
//    public ProductDTO getProductById(Long productId)
//    {
//        Optional<Product> optionalProduct = productRepository.findById(productId);
//
//        if (optionalProduct.isPresent())
//        {
//            return new ProductDTO(optionalProduct.get());
//        }
//        else
//        {
//            throw new ResourceNotFoundException("Product", "Id", productId);
//        }
//    }
//
//    @Override
//    public ProductDTO updateProduct(ProductDTO productDTO, Long productId)
//    {
//        ProductDTO foundProductDTO = getProductById(productId);
//
//        foundProductDTO.setName(productDTO.getName());
//        productRepository.save(getProductFromDTO(foundProductDTO));
//
//        return foundProductDTO;
//    }
//
//    @Override
//    public String removeProduct(Long productId)
//    {
//        productRepository.deleteById(productId);
//
//        return "Product with the Id: " + productId + " successfully deleted!";
//    }
//
//    public Product getProductFromDTO(ProductDTO productDTO)
//    {
//        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice(), productDTO.getCategoryId(), productDTO.getQuantity());
//    }

    public List<Product> getProductsFromDTOs(List<ProductDTO> productDTOs)
    {
        return productDTOs.stream()
                .map(productDTO -> developProduct(productDTO))
                .toList();
    }

    private Product developProduct(ProductDTO productDTO)
    {
        Category foundCategory = categoryRepository.findById(productDTO.getCategory().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", productDTO.getCategory().getId()));

        double specialPrice = productDTO.getPrice() - ((productDTO.getDiscount() * 0.01) * productDTO.getPrice());
        productDTO.setSpecialPrice(specialPrice);

        Product product = new Product(productDTO);
        product.setCategory(foundCategory);

        return product;
    }
//
//    public ProductResponse getProductResponseFromProducts(List<Product> products, Page<Product> productPage)
//    {
//        List<ProductDTO> productsDTOs = new ArrayList<>();
//
//        productsDTOs = products.stream()
//                .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getCategoryId(), product.getQuantity()))
//                .toList();
//
//        return new ProductResponse(productPage.getTotalElements(), productPage.getNumber(), productPage.getSize(), productPage.getTotalPages(), productPage.isLast(), productsDTOs);
//
//    }

}
