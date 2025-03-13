package com.zestindia.t2.service;


import com.zestindia.t2.entity.Product;
import com.zestindia.t2.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

import com.zestindia.t2.service.impl.ProductServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    ProductRepository repository;
    @InjectMocks
    ProductServiceImpl productService;

    private Product mockProduct;

    @BeforeEach
    void initMock() {
        mockProduct = mock(Product.class);

    }

    @Test
    void productSaveTest() {

        doNothing().when(mockProduct).setId(any());
        when(productService.saveProduct(mockProduct)).thenReturn(mock(Product.class));

        assertNotNull(productService.saveProduct(mockProduct));
    }


    @Test
    void productDeleteIfProductExistsTest() {
        when(repository.findById(anyString())).thenReturn(Optional.of(mockProduct));
        doNothing().when(repository).delete(any(Product.class));

        assertTrue(productService.deleteProduct("ID"));
    }

    @Test
    void productDeleteIfProductNOtExistsTest() {
        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        assertFalse(productService.deleteProduct("ID"));
    }

    @Test
    void getProductIfExists() {
        when(repository.findById(anyString())).thenReturn(Optional.of(mockProduct));
        assertNotNull(productService.getProduct("ID"));
    }

    @Test
    void getProductIfNotExists() {
        when(repository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        assertNull(productService.getProduct(anyString()).orElse(null));

    }

    @Test
    void getAllProductTest() {
        assertNotNull(productService.getAllProducts());
    }

    @Test
    void updateProductTestIfNotExists() {

        when(repository.findById("ID")).thenReturn(Optional.ofNullable(null));

        assertFalse(productService.updateProduct(Product.builder().id("ID").build()));
    }
    @Test
    void updateProductTest() {
        Product existingProduct = new Product();
        existingProduct.setId("123");
        existingProduct.setName("Old Name");
        Product updatedProduct = new Product();
        updatedProduct.setId("123");
        updatedProduct.setName("New Name");


        when(repository.findById("123")).thenReturn(Optional.of(existingProduct));
        when(repository.save(any(Product.class))).thenReturn(existingProduct);

        Boolean result = productService.updateProduct(updatedProduct);

        assertTrue(result);


    }

}
