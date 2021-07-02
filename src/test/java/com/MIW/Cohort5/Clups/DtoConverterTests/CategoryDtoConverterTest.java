package com.MIW.Cohort5.Clups.DtoConverterTests;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.services.dtoConverters.CategoryDtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * Tests Category converters from and to DTO
 */

public class CategoryDtoConverterTest {

    CategoryDtoConverter converter = new CategoryDtoConverter();

    @Spy
    List<ProductDto> spyProductDtos = spy(ArrayList.class);

    @Test
    @DisplayName("Test Category model to Dto")
    void testCategoryModelToDto() {
        // arrange
        Category testCategory = new Category("Cocktails", 20);
        CategoryDtoConverter mockCategoryDtoConverter = spy(converter);

        doReturn(spyProductDtos).when(mockCategoryDtoConverter).convertProductList(testCategory.getProducts());

        // act
        CategoryDto testCategoryDto = mockCategoryDtoConverter.toDto(testCategory);

        // assert
        assertThat(testCategoryDto.getCategoryName()).isEqualTo("Cocktails");
        assertThat(testCategoryDto.getCategoryCode()).isEqualTo(20);
    }

    @Test
    @DisplayName("Test CategoryDto to model")
    void testCategoryDtoToModel() {
        // arrange
        CategoryDto testCategoryDto = new CategoryDto();
        testCategoryDto.setCategoryCode(1);
        testCategoryDto.setCategoryName("Cake");

        // act
        Category testCategory = converter.toModel(testCategoryDto);

        // assert
        Assertions.assertAll(
                () -> assertEquals("Cake", testCategory.getCategoryName()),
                () -> assertEquals(1, testCategory.getCategoryCode()));
    }

}