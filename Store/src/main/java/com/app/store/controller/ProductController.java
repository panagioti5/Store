package com.app.store.controller;

import com.app.store.entities.Product;
import com.app.store.exceptions.ExceptionResponse;
import com.app.store.exceptions.ProductNotFoundException;
import com.app.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/product")
    public void saveProduct(@RequestBody Product product) {
        productRepository.save(product);
    }

    @DeleteMapping("/product/delete/{productId}")
    public void deleteProduct(@PathVariable("productId") Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(new ExceptionResponse("No product with ID: " + productId + " found", "13", new Date()));
        }
        productRepository.delete(product.get());
    }

    @PutMapping("/product/update")
    public void updatePRoduct(@RequestBody Product product) {
        Optional<Product> productToUpdate = productRepository.findById(product.getCid());
        if (!productToUpdate.isPresent()) {
            throw new ProductNotFoundException(new ExceptionResponse("No product with ID: " + product.getCid() + " found", "13", new Date()));
        }
        productRepository.save(product);
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw new ProductNotFoundException(new ExceptionResponse("No product with ID: " + productId + " found", "13", new Date()));
        }
        return productRepository.findById(productId).get();
    }
}
