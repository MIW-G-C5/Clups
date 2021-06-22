package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    // this method should only be used when editing existing products, so does not need to return an Optional <Product>
    Product findProductByProductCode(Integer productCode);

    // this method should only be used when editing existing products, so does not need to return an Optional <Product>
    Product findProductByProductName(String productName);

}
