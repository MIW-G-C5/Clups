package com.MIW.Cohort5.Clups.dtos.stateKeeper;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;

/**
 * @author S.K.C.Reijntjes
 *
 * This class helps to keep track of user actions in a session for the editor page.
 */

public class EditorPageStateKeeper {

    private CategoryDto currentCategory;
    private ProductDto currentProduct;

    // this should always be false, until changed by user input in the application
    private boolean showCategoryForm = false;
    private boolean showProductForm = false;

    public EditorPageStateKeeper() {
    }

    public void clearCurrentCategory() {
        this.currentCategory = null;
    }

    public void clearCurrentProduct() {
        this.currentProduct = null;
    }

    public boolean isShowCategoryForm() {
        return showCategoryForm;
    }

    public boolean isShowProductForm() {
        return showProductForm;
    }

    public ProductDto getCurrentProduct() {
        return currentProduct;
    }

    public CategoryDto getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(CategoryDto currentCategory) {
        this.currentCategory = currentCategory;
    }

    public void setCurrentProduct(ProductDto currentProduct) {
        this.currentProduct = currentProduct;
    }

    public void setShowCategoryForm(boolean showCategoryForm) {
        this.showCategoryForm = showCategoryForm;
    }

    public void setShowProductForm(boolean showProductForm) {
        this.showProductForm = showProductForm;
    }
}
