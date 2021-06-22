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

    private String categoryName;

    @Column(unique = true)
    private int categoryCode;

    @OneToMany (mappedBy ="productCategory", fetch = FetchType.EAGER)
    private List<Product> products;

    public Category(String categoryName, int categoryCode, List<Product> products) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
        this.products = products;
    }

    public Category(String categoryName, int categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    public Category() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDbId(Integer categoryDbId) {
        this.categoryDbId = categoryDbId;
    }

    public Integer getCategoryDbId() {
        return categoryDbId;
    }

}
