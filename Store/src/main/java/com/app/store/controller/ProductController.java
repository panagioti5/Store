package com.app.store.controller;

import com.app.store.entities.Product;
import com.app.store.infra.Utils;
import com.app.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            throw Utils.getInstance().getProductNotFoundException(productId);
        }
        productRepository.delete(product.get());
    }

    @PutMapping("/product/update")
    public void updateProduct(@RequestBody Product product) {
        Optional<Product> productToUpdate = productRepository.findById(product.getCid());
        if (!productToUpdate.isPresent()) {
            throw Utils.getInstance().getProductNotFoundException(product.getCid());
        }
        productRepository.save(product);
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (!product.isPresent()) {
            throw Utils.getInstance().getProductNotFoundException(productId);
        }
        return productRepository.findById(productId).get();
    }
}
