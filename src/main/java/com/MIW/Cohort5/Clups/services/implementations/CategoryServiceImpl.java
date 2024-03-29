package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.model.Category;
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
        if (category.getCategoryCode() <= 0) {
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

    @Override
    public CategoryDto findDtoByCode(Integer categoryCode) {
        Category category = categoryRepository.findCategoryByCategoryCode(categoryCode);
        return dtoConverter.toDto(category);
    }

    @Override
    public Category findModelByCode(Integer categoryCode) {
        return categoryRepository.findCategoryByCategoryCode(categoryCode);
    }

    @Override
    public Category deleteCategory(CategoryDto categoryDto) {
        Category model = findModelByCode(categoryDto.getCategoryCode());

        if (isClearToDelete(categoryDto)) {
            categoryRepository.delete(model);
        } else {
            throw new IllegalArgumentException("You can not delete a category that still contains products");
        }

        return model;
    }

    @Override
    public boolean isClearToDelete(CategoryDto categoryDto) {

        if (categoryDto.getProducts() != null) {
            return categoryDto.getProducts().isEmpty();
        } else {
            return true;
        }
    }

}
