package com.MIW.Cohort5.Clups.dtos.stateKeeper;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This class helps to keep track of user actions in a session for the products Page.
 */
public class ProductPageStateKeeper {

    private String categoryName;

    public ProductPageStateKeeper() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
