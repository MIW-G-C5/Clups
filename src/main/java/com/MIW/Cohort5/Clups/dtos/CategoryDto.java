package com.MIW.Cohort5.Clups.dtos;

/**
 * @author S.K.C.Reijntjes
 *
 * This class describes categories with unnecessary data filtered out.
 */

public class CategoryDto {

    private String categoryName;

    private int categoryCode;

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
}
