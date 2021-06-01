package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.repository.ProductRepository;
import com.MIW.Cohort5.Clups.services.ProductService;
import com.MIW.Cohort5.Clups.services.dtoConverters.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This service makes a connection with the product repository.
 */

@Service
public class ProductServiceMySql implements ProductService {

    ProductConverter dtoConverter = new ProductConverter();

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceMySql(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAll() {

        List<Product> models = productRepository.findAll();
        return dtoConverter.toProductDtos(models);
    }

    @Override
    public Product addNew(Product product) {
        return productRepository.save(product);
    }
}
