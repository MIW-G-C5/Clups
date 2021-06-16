package com.MIW.Cohort5.Clups.model;

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
    @Column(unique = true)
    private Integer productCode;

    private String productName;
    private BigDecimal productPrice;

    @ManyToOne
    private Category productCategory;

    public Product(Integer productDbId, String productName, BigDecimal productPrice, Category productCategory) {
        this.productDbId = productDbId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public Product(String productName, BigDecimal productPrice, Category productCategory) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productCategory = productCategory;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public Integer getProductCode() {
        // this method cannot return null to ensure no nullPointerExceptions in the application
        if (productCode == null) {
            return -1;
        } else {
            return productCode;
        }
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public Integer getProductDbId() {
        return productDbId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public void setProductDbId(Integer productDbId) {
        this.productDbId = productDbId;
    }
}