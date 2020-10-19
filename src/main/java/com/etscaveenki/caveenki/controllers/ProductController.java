package com.etscaveenki.caveenki.controllers;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Value("${caveenki.sorted.type}")
    private String sortedType;

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<?> findAllProductsSortedBy(@RequestParam(required = false) String sortedby) {

        List<Product> products;

        if (sortedby == null) {
            products = productService.getAllProductsSortBy(sortedType);
        } else {
            products = productService.getAllProductsSortBy(sortedby);
        }

        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNewProduct(@Valid @RequestBody Product product) {

        return ResponseEntity.ok().body(productService.createNewProduct(product));
    }
}
