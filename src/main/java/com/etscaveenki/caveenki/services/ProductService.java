package com.etscaveenki.caveenki.services;

import com.etscaveenki.caveenki.models.Product;
import com.etscaveenki.caveenki.models.enums.ProductType;
import com.etscaveenki.caveenki.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProductsSortBy (String sortCriteria) {
        return productRepository.findAllProducts(Sort.by(sortCriteria));
    }

    public List<Product> findProductsByProductType(List<String> productTypes, String sortCriteria) {
        List<ProductType> productTypeList = new ArrayList();
        productTypes.stream().forEach(productType -> {
            productTypeList.add(ProductType.valueOf(productType));
        });

        return productRepository.filterProductsByProductType(productTypeList, Sort.by(sortCriteria));
    }

    public Product createOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById (Integer id) {
        productRepository.deleteProductById(id);
    }

    public Optional<Product> findProductById (Integer id) { return productRepository.findProductById(id); }
}
