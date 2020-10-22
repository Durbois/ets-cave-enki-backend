package com.etscaveenki.caveenki.repository;

import com.etscaveenki.caveenki.models.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "select p from Product p ORDER BY productType")
    List <Product> findAllProducts(Sort sort);

    @Transactional
    @Modifying
    @Query(value = "delete from Product p where p.id = :id")
    void deleteProductById(@Param("id") Integer id);
}
