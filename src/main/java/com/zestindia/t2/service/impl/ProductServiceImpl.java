package com.zestindia.t2.service.impl;

import com.zestindia.t2.entity.Product;
import com.zestindia.t2.repository.ProductRepository;
import com.zestindia.t2.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        product.setId(UUID.randomUUID().toString());
        return productRepository.save(product);
    }
    
    @Override
    public Boolean updateProduct(Product product) {
        Optional<Product> optionalProduct = getProduct(product.getId());

        if (optionalProduct.isPresent()) {
            var entity = optionalProduct.get();
            entity.setName(product.getName() != null ? product.getName() : entity.getName());
            entity.setCategory(product.getCategory() != null ? product.getCategory() : entity.getCategory());
            entity.setDescription(product.getDescription() != null ? product.getDescription() : entity.getDescription());
            entity.setPrice(product.getPrice() != null ? product.getPrice() : entity.getPrice());
            productRepository.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(String id) {
        Optional<Product> optionalProduct = getProduct(id);
        if (optionalProduct.isPresent()) {
            productRepository.delete(optionalProduct.get());
            return true;
        } else return false;
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
