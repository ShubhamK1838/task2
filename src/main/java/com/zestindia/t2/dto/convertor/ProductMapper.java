package com.zestindia.t2.dto.convertor;

import com.zestindia.t2.entity.OrderedProduct;
import com.zestindia.t2.entity.Product;

public class ProductMapper {

    public static Product toProduct(OrderedProduct orderedProduct)
    {
        return Product.builder()
                .id(orderedProduct.getId())
                .name(orderedProduct.getName())
                .brand(orderedProduct.getBrand())
                .category(orderedProduct.getCategory())
                .price(orderedProduct.getPrice())
                .quantity(orderedProduct.getQuantity())
                .description(orderedProduct.getDescription())
                .build();
    }
    public static OrderedProduct toOrderdProduct(Product orderedProducts)
    {
        return OrderedProduct.builder()
                .id(orderedProducts.getId())
                .name(orderedProducts.getName())
                .brand(orderedProducts.getBrand())
                .category(orderedProducts.getCategory())
                .price(orderedProducts.getPrice())
                .quantity(orderedProducts.getQuantity())
                .description(orderedProducts.getDescription())
                .build();
    }
}
