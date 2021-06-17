package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.repository.CategoryRepository;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.dtoConverters.CategoryDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This service makes a connection with the category repository.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryDtoConverter dtoConverter = new CategoryDtoConverter();

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // This method sends all objects from a repository to the DTO converter.
    @Override
    public List<CategoryDto> getAll() {

        List<Category> models = categoryRepository.findAll();
        return dtoConverter.toCategoryDtos(models);
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {

        Category newCategory = dtoConverter.toModel(categoryDto);
        Category oldCategory = categoryRepository.findCategoryByCategoryCode(categoryDto.getCategoryCode());

        if (oldCategory != null) {
            newCategory.setCategoryDbId(oldCategory.getCategoryDbId());
        }
        addNew(newCategory);
    }

    //This method saves objects in the database.
    @Override
    public Category addNew(Category category) {


        if (category.getCategoryCode() <= 0){
            category.setCategoryCode(getHighestCategoryCode() + 1);
        }

        return categoryRepository.save(category);
    }


    @Override
    public int getHighestCategoryCode() {
        int categoryCode = 0;

        List<Category> allCategories = categoryRepository.findAll();
        for (Category category : allCategories) {
            if (category.getCategoryCode() > categoryCode) {
                categoryCode = category.getCategoryCode();
            }
        }

        return categoryCode;
    }

    public CategoryDto findDtoByCategoryName(String name) {
       Category model = categoryRepository.findCategoryByCategoryName(name);

        return dtoConverter.toDto(model);
    }

    @Override
    public Category findModelByCategoryName(String name) {
        Category model = categoryRepository.findCategoryByCategoryName(name);

        return model;
    }

    @Override
    public CategoryDto findDtoByCode(Integer categoryCode) {
        Category category = categoryRepository.findCategoryByCategoryCode(categoryCode);
        return dtoConverter.toDto(category);
    }

}
