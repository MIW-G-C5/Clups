package com.MIW.Cohort5.Clups.services;

import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Product;

import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This interface defines the structure of a service.
 */

public interface ProductService {
    List<ProductDto> getAll();

    Product addNew(Product product);
}
