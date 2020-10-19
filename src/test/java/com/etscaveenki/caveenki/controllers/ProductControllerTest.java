package com.etscaveenki.caveenki.controllers;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.models.enums.ContentType;
import com.etscaveenki.caveenki.models.enums.ProductType;
import com.etscaveenki.caveenki.security.services.UserDetailsServiceImpl;
import com.etscaveenki.caveenki.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
// @WebMvcTest(ProductController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {

//    @Autowired
//    private WebApplicationContext context;

    @Autowired
    private TestRestTemplate template;

//    @Autowired
//    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    /*@Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }*/

    @Test
    public void shoudListAllProducts() throws Exception {

        when(productService.getAllProductsSortBy(anyString())).thenReturn(Collections.singletonList(getProduct()));

//        mvc.perform(get("/api/product/all")
//        .contentType(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk())
//        .andExpect(jsonPath("$", hasSize(1)));

        ResponseEntity<?> result = template.getForEntity("/api/product/all", List.class);

        assert(HttpStatus.OK == result.getStatusCode());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    public void shoudCreateProduct(){

    }

    private Product getProduct() {
        return new Product("name", ContentType.BOTTLE, ProductType.RED_WINE,
                10.3, 1000, "Banana;Aroma");
    }

}