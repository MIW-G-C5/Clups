package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;

/**
 * @author S.K.C.Reijntjes
 *
 * This class describes products with unnecessary data filtered out.
 */

public class ProductDto {

    private String productName;

    private int productCode;

    private BigDecimal productPrice;

    public ProductDto() {}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

}
