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

    public boolean equals(ProductDto o) {
        if (this.productCode == o.productCode) {
            return true;
        } else {
            return false;
        }
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    //This getter is grey because Intellij can't make the connection between
    // this method and the fragments it is used in.
    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
    }

    //This getter is grey because Intellij can't make the connection between
    // this method and the fragments it is used in.
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

}
