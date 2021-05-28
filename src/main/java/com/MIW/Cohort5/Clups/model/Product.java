package com.MIW.Cohort5.Clups.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Kimberley Hommes
 *
 * this class describes products
 */
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer productCode;

    private String productName;

    private double productPrice;

    public String getProductName() {
        return productName;
    }

    public Integer getProductCode() {
        return productCode;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
