package com.zestindia.t2.repo;


import com.zestindia.t2.entity.Product;
import com.zestindia.t2.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    Product product;

    @BeforeEach
    public void initProduct() {
        this.product = Product.builder()
                .id("ID")
                .name("PRODUCT").build();
    }


    @Test
    void saveProductTest() {
        when(productRepository.save(any(Product.class))).thenReturn(product);
        var result = productRepository.save(product);

        assertEquals(productRepository.save(product).getId(), product.getId());
    }

    @Test
    void deleteProductTestProduct()
    {
        verify(productRepository,never()).delete(product);
    }

    @Test
    void getProductById()
    {
        when(productRepository.findById(any())).thenReturn(Optional.ofNullable(product));

        assertNotNull(productRepository.findById("ID").orElse(null));
    }

}
