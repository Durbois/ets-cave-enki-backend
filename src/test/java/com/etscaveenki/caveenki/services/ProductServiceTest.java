package com.etscaveenki.caveenki.services;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.models.enums.ContentType;
import com.etscaveenki.caveenki.models.enums.ProductType;
import com.etscaveenki.caveenki.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldFindAllProducts () {
        when(productRepository.findAllProducts(any())).thenReturn(Collections.singletonList(getProduct()));

        List<Product> products = productService.getAllProductsSortBy("contentType");

        assert(products.size() == 1);
    }

    @Test
    public void shouldFilterProductsByProductType () {
        when(productRepository.filterProductsByProductType(any(), any())).thenReturn(Collections.singletonList(getProduct()));

        List<Product> products = productService.findProductsByProductType(Arrays.asList("RED_WINE"), "contentType");

        assert(products.size() == 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowException () {
        when(productRepository.filterProductsByProductType(any(), any())).thenReturn(Collections.singletonList(getProduct()));

        productService.findProductsByProductType(Arrays.asList("RED"), "contentType");
    }

    @Test
    public void shouldCreateNewProduct() {
        when(productRepository.save(any())).thenReturn(getProduct());

        Product product = productService.createOrUpdateProduct(getProduct());

        assert(product != null);
        assert(product.getName().equals("name"));
    }

    @Test
    public void shoudFindProductById() {
        when(productRepository.findProductById(anyInt())).thenReturn(Optional.of(getProduct()));

        Optional<Product> result = productService.findProductById(2);

        assert(result != null);
    }

    private Product getProduct() {
        return new Product("name", ContentType.BOTTLE, ProductType.RED_WINE,
                10.3, 1000, "Banana;Aroma");
    }
}