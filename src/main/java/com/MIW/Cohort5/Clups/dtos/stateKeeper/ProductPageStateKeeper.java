package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.ProductDto;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This class helps to keep track of user actions in a session for the products Page.
 */

public class ProductPageStateKeeper {

    private ProductDto currentProduct;
    private Integer currentCategoryCode;
    // this should always be false, until changed by user input in the application
    private boolean showForm = false;

    public ProductPageStateKeeper() {
    }

    public void clearCurrentProduct() {
        this.currentProduct = null;
    }

    public ProductDto getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(ProductDto currentProduct) {
        this.currentProduct = currentProduct;
    }

    public Integer getCurrentCategoryCode() {
        return currentCategoryCode;
    }

    public void setCurrentCategoryCode(Integer currentCategoryCode) {
        this.currentCategoryCode = currentCategoryCode;
    }

    public boolean isShowForm() {
        return showForm;
    }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

}
