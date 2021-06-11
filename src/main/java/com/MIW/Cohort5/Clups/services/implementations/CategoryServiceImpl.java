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

    //This method saves objects in the database.
    @Override
    public Category addNew(Category category) {
        return categoryRepository.save(category);
    }

    public CategoryDto findDtoByCategoryName(String name) {
        List<CategoryDto> allCategories = getAll();

        CategoryDto categoryByName = null;
        for (CategoryDto category : allCategories) {
            if (category.getCategoryName().equals(name)) {
                categoryByName = category;
            }
        }

        return categoryByName;
    }

    @Override
    public Category findModelByCategoryName(String name) {
        List<Category> categories = categoryRepository.findAll();

        Category category = null;
        for (Category cat : categories) {
            if(cat.getCategoryName().equals(name)) {
                category = cat;
            }
        }

        return category;
    }

}
