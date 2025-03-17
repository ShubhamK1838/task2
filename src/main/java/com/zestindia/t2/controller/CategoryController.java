package com.zestindia.t2.controller;


import com.zestindia.t2.entity.Category;
import com.zestindia.t2.exception.custom.CategoryNotFoundException;
import com.zestindia.t2.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategoryDetails(@PathVariable String id) {
        categoryService.delete(id);
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void getCategory(@PathVariable String id) {
        categoryService.getCategory(id).orElseThrow(CategoryNotFoundException::new);
    }


    @GetMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @PutMapping
    public void updateCategory(@RequestBody Category category) {
        categoryService.update(category);
    }


}
