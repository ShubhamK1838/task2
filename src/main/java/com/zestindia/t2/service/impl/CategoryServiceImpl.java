package com.zestindia.t2.service.impl;

import com.zestindia.t2.entity.Category;
import com.zestindia.t2.exception.custom.CategoryNotFoundException;
import com.zestindia.t2.repository.CategoryRepository;
import com.zestindia.t2.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {

        category.setId(UUID.randomUUID().toString());

        return categoryRepository.save(category);
    }

    @Override
    public void update(Category category) {
        Category entity = getCategory(category.getId()).orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + category.getId()));
        entity.setName(category.getName() != null ? category.getName() : category.getName());
        categoryRepository.save(entity);
    }

    @Override
    public void delete(String id) {
        categoryRepository.delete(getCategory(id).orElseThrow(() -> new CategoryNotFoundException("Category not found with ID: " + id)));
    }

    @Override
    public Optional<Category> getCategory(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public Category putCategoryByName(String name) {
        Optional<Category> optional = getByName(name);
        if (optional.isPresent())
            return optional.get();
        else {
            var category = Category.builder().name(name).build();
            category.setId(UUID.randomUUID().toString());
            categoryRepository.save(category);
            return categoryRepository.save(category);
        }
    }
}
