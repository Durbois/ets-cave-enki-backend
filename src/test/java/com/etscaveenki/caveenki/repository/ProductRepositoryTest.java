package com.etscaveenki.caveenki.repository;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.models.enums.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Sql("product.sql")
    public void productShouldBeSortedByContentType() {
        List<Product> products = productRepository.findAllProducts( Sort.by("contentType") );

        assert(products.size() == 5);
        assert(products.get(0).getName().equals("bordeaux"));
        assert(products.get(1).getName().equals("bull"));
        assert(products.get(2).getName().equals("rose") && products.get(2).getContentType() == ContentType.BOTTLE);
        assert(products.get(3).getName().equals("rose") && products.get(3).getContentType() == ContentType.CAN);
        assert(products.get(4).getName().equals("rose") && products.get(4).getContentType() == ContentType.SACHET);
    }

    @Test
    @Sql("product.sql")
    public void productShouldBeDeletedById() {
        productRepository.deleteProductById(5);

        List<Product> products = productRepository.findAllProducts( Sort.by("contentType") );

        assert(products.size() == 4);
        products.stream().forEach(product  -> {
            assertThat(!product.getName().equals("bordeaux"));
        });
    }

    @Test
    @Sql("product.sql")
    public void productShouldBeFoundById() {
        Optional<Product> optionalProduct = productRepository.findProductById(4);

        assertThat(optionalProduct.isPresent());
        assertThat(optionalProduct.get().getName().equals("bull"));
    }
}