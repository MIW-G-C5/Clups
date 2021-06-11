package com.MIW.Cohort5.Clups.services.dtoConverters;

import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This class converts products to a DTO to filter out any unnecessary data.
 */

public class ProductDtoConverter {

    //This method calls the toDto method for all objects from a database table.
    public List<ProductDto> toProductDtos(List<Product> models) {
        List<ProductDto> result = new ArrayList<>();

        for (Product model : models) {
            ProductDto dto = toDto(model);
            result.add(dto);
        }

        return result;
    }

    //This converts an objects to a DTO
    public ProductDto toDto(Product model) {
        ProductDto result = new ProductDto();
        result.setProductName(model.getProductName());
        result.setProductCode(model.getProductCode());
        result.setProductPrice(model.getProductPrice());
        result.setCategoryName(model.getProductCategory().getCategoryName());

        return result;
    }

    public Product toModel(Category category, ProductDto dto) {
        Product model = new Product();

        model.setProductName(dto.getProductName());
        model.setProductPrice(dto.getProductPrice());

        model.setProductCategory(category);

        return model;
    }

}
