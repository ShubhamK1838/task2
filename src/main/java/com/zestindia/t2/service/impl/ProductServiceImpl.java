package com.zestindia.t2.service.impl;

import com.zestindia.t2.entity.Category;
import com.zestindia.t2.entity.Product;
import com.zestindia.t2.exception.custom.CategoryNotFoundException;
import com.zestindia.t2.exception.custom.ProductNotFoundException;
import com.zestindia.t2.repository.ProductRepository;
import com.zestindia.t2.service.CategoryService;
import com.zestindia.t2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;


    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    public Product saveProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        List<Category> categories = product.getCategory()
                .stream()
                .map(ele -> {
                    return categoryService.putCategoryByName(ele.getName());
                })
                .collect(Collectors.toList());

        product.setCategory(categories);
        return productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {

        var entity = getProduct(product.getId()).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + product.getId()));
        entity.setName(product.getName() != null ? product.getName() : entity.getName());
        entity.setCategory(product.getCategory() != null ? product.getCategory() : entity.getCategory());
        entity.setDescription(product.getDescription() != null ? product.getDescription() : entity.getDescription());
        entity.setPrice(product.getPrice() != null ? product.getPrice() : entity.getPrice());
        entity.setQuantity(product.getQuantity() != null ? product.getQuantity() : entity.getQuantity());
        entity.setBrand(product.getBrand() != null ? product.getBrand() : entity.getBrand());
        productRepository.save(entity);

    }

    @Override
    public void deleteProduct(String id) {
        productRepository.delete(getProduct(id).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id)));
    }

    @Override
    public Optional<Product> getProduct(String id) {
        return productRepository.findById(id);

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
