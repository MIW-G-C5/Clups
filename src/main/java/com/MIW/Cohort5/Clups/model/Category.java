package com.MIW.Cohort5.Clups.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author S.K.C.Reijntjes
 *
 * All product items are assigned to a category.
 */

@Entity
public class Category {
    @Id
    @GeneratedValue
    private Integer categoryCode;

    @Column(unique = true)
    private String categoryName;

    @OneToMany
    private List<Product> products;



    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public Integer getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
