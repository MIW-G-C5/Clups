package com.MIW.Cohort5.Clups.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private Integer productCode;

    private String productName;

    private BigDecimal productPrice;

    public Product(Integer productCode, String productName, BigDecimal productPrice) {
        this.productCode = productCode;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product(String productName, BigDecimal productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product() {
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

}