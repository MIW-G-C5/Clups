package com.MIW.Cohort5.Clups.DtoConverterTests;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.services.dtoConverters.CategoryDtoConverter;
import com.MIW.Cohort5.Clups.services.dtoConverters.ProductDtoConverter;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author S.K.C.Reijntjes
 *
 * This class tests methods in the ProductDtoConverter class
 */

public class ProductDtoConverterTests {

    @Test
    void productToDtoTest() {
        ProductDtoConverter productDtoConverter = new ProductDtoConverter();
        Category category = new Category("Cocktails", 38);
        Product product = new Product("Bloody Mary", BigDecimal.valueOf(2.50), category);

        ProductDto productDto = productDtoConverter.toDto(product);

        assertThat(productDto.getProductName()).isEqualTo("Bloody Mary");
        assertThat(productDto.getProductPrice()).isEqualTo(BigDecimal.valueOf(2.50));
        assertThat(productDto.getCategoryCode()).isEqualTo(38);
    }


}
