package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.repository.ProductRepository;
import com.MIW.Cohort5.Clups.services.ProductService;
import com.MIW.Cohort5.Clups.services.dtoConverters.ProductDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This service makes a connection with the product repository.
 */

@Service
public class ProductServiceImpl implements ProductService {

    ProductDtoConverter dtoConverter = new ProductDtoConverter();

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // This method sends all objects from a repository to the DTO converter.
    @Override
    public List<ProductDto> getAll() {

        List<Product> models = productRepository.findAll();
        return dtoConverter.toProductDtos(models);
    }

    //todo weghalen
//    public List<Product> getByCategory(String categoryName) {
//        List<Product> productsByCategory = productRepository.findProductsByCategory(categoryName);
//        return dtoConverter.toProductDtos(models);
//    }

    //This method saves objects in the database.
    @Override
    public Product addNew(Product product) {
        return productRepository.save(product);
    }

}
