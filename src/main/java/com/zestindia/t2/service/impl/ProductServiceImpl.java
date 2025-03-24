package com.zestindia.t2.service.impl;

import com.zestindia.t2.entity.Category;
import com.zestindia.t2.entity.Product;
import com.zestindia.t2.service.CategoryService;
import com.zestindia.t2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl extends MasterEntityServiceImpl<Product> implements ProductService {


    @Autowired
    private CategoryService categoryService;


    @Override
    public Product save(Product product) {
        product.setId(UUID.randomUUID().toString());
        List<Category> categories = product.getCategory()
                .stream()
                .map(ele -> {
                    return categoryService.putCategoryByName(ele.getName());
                })
                .collect(Collectors.toList());

        product.setCategory(categories);
        return super.save(product);
    }


}
