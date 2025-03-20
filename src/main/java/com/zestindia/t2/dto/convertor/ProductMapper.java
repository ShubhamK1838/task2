package com.zestindia.t2.dto.convertor;

import com.zestindia.t2.entity.OrderedProduct;
import com.zestindia.t2.entity.Product;

public class ProductMapper {


    public static OrderedProduct toOrderedProduct(Product orderedProducts)
    {
        return OrderedProduct.builder()
                .productId(orderedProducts.getId())
                .name(orderedProducts.getName())
                .brand(orderedProducts.getBrand())
                .category(orderedProducts.getCategory())
                .price(orderedProducts.getPrice())
                .quantity(orderedProducts.getQuantity())
                .description(orderedProducts.getDescription())
                .build();
    }
}
