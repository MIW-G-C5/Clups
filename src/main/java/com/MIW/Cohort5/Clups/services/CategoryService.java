package com.MIW.Cohort5.Clups.services;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.model.Category;

import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This interface defines the structure of a category service.
 */

public interface CategoryService {
    List<CategoryDto> getAll();

    void saveCategory(CategoryDto categoryDto);

    Category addNew(Category category);

    int getHighestCategoryCode();

    CategoryDto findDtoByCode(Integer categoryCode);

    Category findModelByCode(Integer categoryCode);

    Category deleteCategory(CategoryDto categoryDto);

    boolean isClearToDelete(CategoryDto categoryDto);

}
