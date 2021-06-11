package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.ProductDto;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This class helps to keep track of user actions in a session for the products Page.
 */
public class ProductPageStateKeeper {

    private String categoryName;

    private ProductDto currentProduct;

    // this should always be false, until changed by user input in the application
    private boolean showForm = false;

    public ProductPageStateKeeper() {
    }

    public void clearCurrentProduct() {
        this.currentProduct = null;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ProductDto getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(ProductDto currentProduct) {
        this.currentProduct = currentProduct;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }
}