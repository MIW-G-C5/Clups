package com.MIW.Cohort5.Clups.dtos;

import java.math.BigDecimal;

/**
 * @author S.K.C.Reijntjes
 *
 * This class describes products with unnecessary data filtered out.
 */

public class ProductDto {

    private String productName;

    private BigDecimal productPrice;

    public ProductDto() {}

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
}
