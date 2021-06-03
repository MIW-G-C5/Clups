package com.MIW.Cohort5.Clups.model;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author Kimberley Hommes
 *
 * This class describes products
 */

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer productDbId;

    // this productcode can be used to compare products and can safely be transferred to a productDto object
    private static int productCodeCounter = 0;

    @Column(unique = true)
    private int productCode;

    private String productName;
    private BigDecimal productPrice;

    @ManyToOne
    private Category productCategory;

    public Product(Integer productDbId, String productName, BigDecimal productPrice, Category productCategory) {
        this.productDbId = productDbId;
        addProductCode();
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public Product(String productName, BigDecimal productPrice, Category productCategory) {
        this.productName = productName;
        addProductCode();
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    private void addProductCode() {
        this.productCode = productCodeCounter + 1;
        productCodeCounter++;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public int getProductCode() {
        return productCode;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Category getProductCategory() {
        return productCategory;
    }

}