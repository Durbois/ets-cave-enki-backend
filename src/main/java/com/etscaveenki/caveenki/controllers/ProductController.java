package com.etscaveenki.caveenki.controllers;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${caveenki.sorted.type}")
    private String sortedType = "productType";

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllProductsSortedBy(@RequestParam(required = false) String sortedBy) {

        List<Product> products;

        if (sortedBy == null) {
            products = productService.getAllProductsSortBy(sortedType);
        } else {
            products = productService.getAllProductsSortBy(sortedBy);
        }

        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createNewProduct(@Valid @RequestBody Product product) {

        return ResponseEntity.ok().body(productService.createNewProduct(product));
    }
}
