package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;

/**
 * @author S.K.C.Reijntjes
 *
 * This class describes products with unnecessary data filtered out.
 */

public class ProductDto {
    
    private String productName;

    private Integer productCode;

    private BigDecimal productPrice;

    private Integer categoryCode;

    public ProductDto() {}

    public ProductDto(String productName, int productCode, BigDecimal productPrice) {
        this.productName = productName;
        this.productCode = productCode;
        this.productPrice = productPrice;
    }

    public boolean equals(ProductDto other) {
        return this.productCode == other.productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductCode() {
        if (productCode == null) {
            return -1;
        } else {
            return productCode;
        }
    }

    public void setProductCode(Integer productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

}
