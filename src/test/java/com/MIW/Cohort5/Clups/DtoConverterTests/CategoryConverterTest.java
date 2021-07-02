package com.MIW.Cohort5.Clups.DtoConverterTests;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.services.dtoConverters.CategoryDtoConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 */

public class CategoryConverterTest {

    private CategoryDtoConverter converter = new CategoryDtoConverter();

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

    @Test
    @DisplayName("Test Category model to Dto")
    void testCategoryModelToDto() {
        // arrange
        Category testCategory = new Category("Cocktails", 20);

        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(new ProductDto("Mojito", 20, BigDecimal.valueOf(5)));
        productDtos.add(new ProductDto("Aperol Spritz", 21, BigDecimal.valueOf(6)));

        CategoryDtoConverter mockConverter = Mockito.spy(converter);
        Mockito.doReturn(productDtos).when(mockConverter).convertProductList(testCategory.getProducts());

        // act
        CategoryDto testCategoryDto = mockConverter.toDto(testCategory);
        List<ProductDto> testProducts = testCategoryDto.getProducts();

        // assert
        assertAll(
                () -> assertEquals(2, testCategoryDto.getProducts().size()),
                () -> assertEquals("Cocktails", testCategoryDto.getCategoryName()),
                () -> assertEquals("Mojito", testProducts.get(0).getProductName()));
    }

}
