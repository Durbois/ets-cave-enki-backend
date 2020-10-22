package com.etscaveenki.caveenki.controllers;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.models.enums.ContentType;
import com.etscaveenki.caveenki.models.enums.ProductType;
import com.etscaveenki.caveenki.services.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() {
       // ReflectionTestUtils.setField(ProductController.class, "sortedType", "productType");
    }

    @Test
    public void shoudListAllProducts() throws Exception {

        when(productService.getAllProductsSortBy(anyString())).thenReturn(Collections.singletonList(getProduct()));

        ResponseEntity<?> responseEntity = restTemplate.getForEntity("/api/product/all", List.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(responseEntity.getBody().equals(Collections.singletonList(getProduct())));
    }

    @Test
    // @WithMockUser(roles = {"ADMIN"})
    public void shoudCreateProduct(){

        when(productService.createOrUpdateProduct(any(Product.class))).thenReturn(getProduct());

        ResponseEntity<?> responseEntity = restTemplate.postForEntity("/api/product", getProduct(), Product.class);

        assertThat(responseEntity.getBody().equals(getProduct()));
    }

    @Test
    // @WithMockUser(roles = {"ADMIN"})
    public void shoudDeleteProduct(){

        restTemplate.delete("/api/product/1");
    }

    private Product getProduct() {
        return new Product("product", ContentType.BOTTLE, ProductType.RED_WINE,
                10.3, 1000, "Banana;Aroma");
    }

}