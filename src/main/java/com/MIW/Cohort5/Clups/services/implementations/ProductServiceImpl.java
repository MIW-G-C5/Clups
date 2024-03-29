package com.MIW.Cohort5.Clups.services.implementations;

import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.repository.CategoryRepository;
import com.MIW.Cohort5.Clups.repository.ProductRepository;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import com.MIW.Cohort5.Clups.services.dtoConverters.ProductDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * This service makes a connection with the product repository.
 */

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDtoConverter dtoConverter = new ProductDtoConverter();

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            CategoryService categoryService) {

        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    // This method sends all objects from a repository to the DTO converter.
    @Override
    public List<ProductDto> getAll() {

        List<Product> models = productRepository.findAll();
        return dtoConverter.toProductDtos(models);
    }

    //This method saves objects in the database from application input
    @Override
    public void saveProduct(ProductDto productDto) {
        Category category = categoryService.findModelByCode(productDto.getCategoryCode());

        Product newProduct = dtoConverter.toModel(category, productDto);

        Product oldProduct = productRepository.findProductByProductCode(productDto.getProductCode());


        if (oldProduct != null) {
                newProduct.setProductDbId(oldProduct.getProductDbId());
            }

        addNew(newProduct);
    }

    //This method saves objects in the database.
    @Override
    public Product addNew(Product product) {
        // if a product does not yet have a productCode (i.e. it does not yet exist in the database),
        // generate an new productCode, both for use in the application and for seeder to (re)build database
        if (product.getProductCode() <= 0) {
            product.setProductCode(getHighestProductCode() + 1);
        }

        return productRepository.save(product);
    }

    @Override
    public List<ProductDto> getProductsByCategory(Integer selectedCategoryCode) {
        List<ProductDto> productsByCategory = new ArrayList<>();
        CategoryServiceImpl categoryService = new CategoryServiceImpl(categoryRepository);
        if (selectedCategoryCode != null) {
            productsByCategory = categoryService.findDtoByCode(selectedCategoryCode).getProducts();
        }

        return productsByCategory;
    }

    @Override
    public ProductDto findDtoByCode(int productCode) {
        Product product = productRepository.findProductByProductCode(productCode);

        return dtoConverter.toDto(product);
    }

    @Override
    public int getHighestProductCode() {
        int productCode = 0;

        List<Product> allProducts = productRepository.findAll();
        for (Product product : allProducts) {
            if (product.getProductCode() > productCode) {
                productCode = product.getProductCode();
            }
        }

        return productCode;
    }

    public void deleteProduct(ProductDto productDto) {
        productRepository.delete(productRepository.findProductByProductCode(productDto.getProductCode()));
    }

}
