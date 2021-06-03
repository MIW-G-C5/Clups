package com.MIW.Cohort5.Clups.dtos;

import com.MIW.Cohort5.Clups.model.Product;

import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This class describes categories with unnecessary data filtered out.
 */

public class CategoryDto {

    private String categoryName;

    private int categoryCode;

    private List<Product> products;

    public CategoryDto() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
