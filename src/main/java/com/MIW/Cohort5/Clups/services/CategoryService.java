package com.MIW.Cohort5.Clups.services;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;

import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This interface defines the structure of a category service.
 */

public interface CategoryService {
    List<CategoryDto> getAll();
    
    Category addNew(Category category);
}
