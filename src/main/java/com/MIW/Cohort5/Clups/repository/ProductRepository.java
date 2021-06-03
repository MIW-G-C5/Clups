package com.MIW.Cohort5.Clups.repository;

import com.MIW.Cohort5.Clups.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    //todo weghalen
//    List<Product> findProductsByCategory(String categoryName);
}
