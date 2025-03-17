package com.zestindia.t2.service;

import com.zestindia.t2.entity.Product;

import java.util.Optional;

public interface ProductService {

    Product saveProduct(Product product);

    void  updateProduct(Product product);

    void  deleteProduct(String id);

    Optional<Product> getProduct(String id );

    java.util.List<Product> getAllProducts();

}
