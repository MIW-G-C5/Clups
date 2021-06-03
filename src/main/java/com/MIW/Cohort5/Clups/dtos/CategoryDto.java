package com.MIW.Cohort5.Clups.dtos;
import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This class describes categories with unnecessary data filtered out.
 */

public class CategoryDto {

    private String categoryName;

    private int categoryCode;

    private List<ProductDto> products;

    public CategoryDto() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    //This getter is used in the CategoryDtoConverter
    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

}
