package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This class helps to keep track of user actions in a session for the products Page.
 */
public class ProductPageStateKeeper {

    private String categoryName;

    private ProductDto currentProduct;

    private CategoryDto currentCategory;

    // this should always be false, until changed by user input in the application
    private boolean showForm = false;
    private boolean showCatForm = false;
    private boolean showUserForm = false;

    public ProductPageStateKeeper() {
    }

    public void clearCurrentProduct() {
        this.currentProduct = null;
    }

    public void clearCurrentCategory() {
        this.currentCategory = null;
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

    public CategoryDto getCurrentCategory() {return currentCategory;}

    public void setCurrentCategory(CategoryDto currentCategory) {this.currentCategory = currentCategory;}

    public boolean isShowForm() {
        return showForm;
    }

    public boolean isShowCatForm() { return showCatForm; }

    public boolean isShowUserForm() { return showUserForm; }

    public void setUserForm(boolean showUserForm) { this.showUserForm = showUserForm; }

    public void setShowForm(boolean showForm) {
        this.showForm = showForm;
    }

    public void setShowCatForm(boolean showCatForm) { this.showCatForm = showCatForm; }
}
