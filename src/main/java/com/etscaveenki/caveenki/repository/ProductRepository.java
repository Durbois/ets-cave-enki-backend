package com.etscaveenki.caveenki.repository;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.models.enums.ProductType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    @Query(value = "select p from Product p ORDER BY productType")
    List <Product> findAllProducts(Sort sort);

    @Query(value = "select p from Product p where p.productType in :productTypes")
    List <Product> filterProductsByProductType(@Param("productTypes") Collection<ProductType> productTypes, Sort sort);

    @Query(value = "select p from Product p where p.id = :id ")
    Optional<Product> findProductById(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query(value = "delete from Product p where p.id = :id")
    void deleteProductById(@Param("id") Integer id);

}
