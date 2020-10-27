package com.etscaveenki.caveenki.models;

import com.etscaveenki.caveenki.models.enums.ContentType;
import com.etscaveenki.caveenki.models.enums.ProductType;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "contenttype")
    private ContentType contentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "producttype")
    private ProductType productType;

    @NotBlank
    @Column(name = "unitprice")
    private double unitPrice;

    @NotBlank
    @Column(name = "boxprice")
    private double boxPrice;

    @Column(name = "ingredients")
    private String ingredients;

    public Product() {
    }

    public Product(String name, ContentType contentType, ProductType productType,
                   double unitPrice, double boxPrice, String ingredients) {
        this.name = name;
        this.contentType = contentType;
        this.productType = productType;
        this.unitPrice = unitPrice;
        this.boxPrice = boxPrice;
        this.ingredients = ingredients;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getBoxPrice() {
        return boxPrice;
    }

    public void setBoxPrice(double boxPrice) {
        this.boxPrice = boxPrice;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
