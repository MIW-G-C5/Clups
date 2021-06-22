package com.MIW.Cohort5.Clups.dtos.stateKeeper;
import com.MIW.Cohort5.Clups.dtos.CategoryDto;

/**
 * @author Petrina IJnsen
 * Opdracht:
 * Doel: This class helps to keep track of user actions in a session for the Category Page.
 */

public class CategoryPageStateKeeper {

    private String categoryName;
    private CategoryDto currentCategory;

    private boolean showCatForm = false;

    public CategoryPageStateKeeper() { }

    public void clearCurrentCategory() {
        this.currentCategory = null;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public CategoryDto getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(CategoryDto currentCategory) {
        this.currentCategory = currentCategory;
    }

    public boolean isShowCatForm() {
        return showCatForm;
    }

    public void setShowCatForm(boolean showCatForm) {
        this.showCatForm = showCatForm;
    }

}
