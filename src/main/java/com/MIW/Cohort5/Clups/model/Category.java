package com.MIW.Cohort5.Clups.model;

import javax.persistence.*;
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
    private Integer categoryDbId;

    @Column(unique = true)
    private String categoryName;

    @Column(unique = true)
    private int categoryCode;

    @OneToMany
    private List<Product> products;

    public Category(String categoryName, int categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    public Category() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public Integer getCategoryDbId() {
        return categoryDbId;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
