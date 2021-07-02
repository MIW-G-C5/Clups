package com.MIW.Cohort5.Clups.DtoConverterTests;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.services.dtoConverters.CategoryDtoConverter;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.mockito.Mockito.*;

class CategoryDtoConverterTest {

    CategoryDtoConverter converter = new CategoryDtoConverter();

    @Spy
    List<ProductDto> spyProductDtos = spy(ArrayList.class);

    @Test
    void CategoryDtoConverterTest() {
        Category testCategory = new Category("Cocktails", 20);
        CategoryDtoConverter mockCategoryDtoConverter = spy(converter);

        doReturn(spyProductDtos).when(mockCategoryDtoConverter).convertProductList(testCategory.getProducts());

        CategoryDto testCategoryDto = mockCategoryDtoConverter.toDto(testCategory);

        assertThat(testCategoryDto.getCategoryName()).isEqualTo("Cocktails");
        assertThat(testCategoryDto.getCategoryCode()).isEqualTo(20);
    }



}