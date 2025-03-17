package com.zestindia.t2.service;


import com.zestindia.t2.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category save(Category category);

    void  update(Category category);

    void  delete(String id);

    Optional<Category> getCategory(String id);

    List<Category> getAll();
    Optional<Category> getByName(String name );

    Category putCategoryByName(String name );

}
