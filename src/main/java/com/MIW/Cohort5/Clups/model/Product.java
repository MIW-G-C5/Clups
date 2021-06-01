package com.MIW.Cohort5.Clups.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    private double productPrice;

    public String getProductName() {
        return productName;
    }

    public Integer getProductCode() {
        return productCode;
    } //todo is deze nodig?

    public double getProductPrice() {
        return productPrice;
    }
}
