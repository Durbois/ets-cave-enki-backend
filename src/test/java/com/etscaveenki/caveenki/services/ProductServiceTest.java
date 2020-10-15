package com.etscaveenki.caveenki.services;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.models.enums.ContentType;
import com.etscaveenki.caveenki.models.enums.ProductType;
import com.etscaveenki.caveenki.repository.ProductRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldFindAllProducts () {

        final Product product = new Product("name", ContentType.BOTTLE, ProductType.RED_WINE,
        10.3, 1000, "Banana;Aroma");

        given(productRepository.findAllProducts(any())).willReturn(Collections.singletonList(product));


    }
}