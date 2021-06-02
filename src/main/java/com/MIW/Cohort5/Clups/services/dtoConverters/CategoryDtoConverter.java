package com.MIW.Cohort5.Clups.services.dtoConverters;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This class converts categories to a DTO to filter out any unnecessary data.
 */

public class CategoryDtoConverter {

    //This method calls the toDto method for all objects from a database table.
    public List<CategoryDto> toCategoryDtos(List<Category> models) {
        List<CategoryDto> result = new ArrayList<>();

        for (Category model : models) {
            CategoryDto dto = toDto(model);
            result.add(dto);
        }

        return result;
    }

    //This converts an objects to a DTO
    public CategoryDto toDto(Category model) {
        CategoryDto result = new CategoryDto();
        result.setCategoryName(model.getCategoryName());
        result.setCategoryCode(model.getCategoryCode());

        return result;
    }
}
