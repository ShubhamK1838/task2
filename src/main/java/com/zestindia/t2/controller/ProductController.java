package com.zestindia.t2.controller;

import com.zestindia.t2.entity.Product;
import com.zestindia.t2.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {


    public ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable String productId) {
        Optional<Product> productOptional = productService.get(productId);
        return
                productOptional.isPresent() ?
                        ResponseEntity.ok().body(productOptional.get())
                        : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        List<Product> list = productService.getAll();
        return list.size() > 0
                ? ResponseEntity.ok().body(list)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("No products Available.");
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void updateProductDetails(@RequestBody Product product) {
        productService.update(product.getId(), product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Secured("ROLE_ADMIN")
    public void deleteProductDetails(@PathVariable String id) {
        productService.removeById(id);
    }
}
