package com.etscaveenki.caveenki.controllers;

import com.etscaveenki.caveenki.dtos.responses.MessageResponse;
import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${caveenki.sorted.type}")
    private String sortedType;

    @Autowired
    private ProductService productService;

    @Operation(summary = "Get a list of products, could be filtered by productTypes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of products",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class)) }),
            @ApiResponse(responseCode = "400", description = "The requestParam doest match the enum type",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MessageResponse.class)) })
    })
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllProductsSortedBy(@RequestParam(required = false) List<String> productTypes) {

        List<Product> products;

        if (productTypes == null) {
           products = productService.getAllProductsSortBy(sortedType);
        } else {
          try {
             products = productService.findProductsByProductType(productTypes, sortedType);
           } catch (IllegalArgumentException e) {
               return ResponseEntity.badRequest().body(new MessageResponse("Please check the requestParam productTypes again!"));
           }
        }

        return ResponseEntity.ok().body(products);
    }

    @Operation(summary = "Create a new product Item", security = @SecurityRequirement(name = "bearerAuth"))
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createOrUpdateProduct(@Valid @RequestBody Product product) {

        return ResponseEntity.ok().body(productService.createOrUpdateProduct(product));
    }

    @Operation(summary = "Delete a new product by Id", security = @SecurityRequirement(name = "bearerAuth"))
    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Integer id ) {
        productService.deleteProductById(id);

        return ResponseEntity.ok(new MessageResponse("Product successfully removed"));
    }

    @Operation(summary = "Get a product by Id")
    @GetMapping(value = "/item/{id}")
    public ResponseEntity<?> findProductById (@PathVariable("id") Integer id) {
        Optional<Product> optionalProduct = productService.findProductById(id);

        if(optionalProduct.isEmpty()) {
            return ResponseEntity.badRequest().body(new MessageResponse("Product doesn't exist!"));
        }
        return ResponseEntity.ok().body(optionalProduct.get());
    }
}
