package com.MIW.Cohort5.Clups.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Kimberley Hommes
 */
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer productCode;

    private String productName;

    public String getProductName() {
        return productName;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
