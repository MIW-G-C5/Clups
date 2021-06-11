package com.MIW.Cohort5.Clups.services.dtoConverters;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;
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

    public Category toModel(CategoryDto dto){
        Category model = new Category();
        model.setCategoryName(dto.getCategoryName());
        model.setCategoryCode(dto.getCategoryCode());
        return model;
    }

    //This converts an objects to a DTO
    public CategoryDto toDto(Category model) {
        CategoryDto result = new CategoryDto();
        result.setCategoryName(model.getCategoryName());
        result.setCategoryCode(model.getCategoryCode());
        result.setProducts(convertProductList(model.getProducts()));
        return result;
    }

    public List<ProductDto> convertProductList(List<Product> products) {
        ProductDtoConverter productDtoConverter = new ProductDtoConverter();
        return productDtoConverter.toProductDtos(products);
    }

}
