package com.etscaveenki.caveenki.repository;

import com.etscaveenki.caveenki.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "select p from Product p ORDER BY productType")
    List <Product> findAllProducts(Sort sort);
}
