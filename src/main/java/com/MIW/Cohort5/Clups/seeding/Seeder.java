package com.MIW.Cohort5.Clups.seeding;

import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import com.MIW.Cohort5.Clups.services.implementations.ClupsUserDetailsServiceImpl;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author S.K.C.Reijntjes
 *
 * This class adds testdata to the database
 */

@Component
public class Seeder {

    private ProductService productService;
    private CategoryService categoryService;
    private ClupsUserDetailsServiceImpl clupsUserDetailsService;

    public Seeder(ProductService productService,  ClupsUserDetailsServiceImpl clupsUserDetailsService,
                  CategoryService categoryService) {
        this.productService = productService;
        this.clupsUserDetailsService = clupsUserDetailsService;
        this.categoryService = categoryService;
    }

    // This generates the test data when launching the program.
    // The database must be reset manually by clearing all data in MySQL.
    // The event variable is grey because it only exists when running the program.
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedProduct();
        seedUser();
        seedCategory();
    }

    private void seedUser() {
        if (clupsUserDetailsService.getAll().size() == 0) {
            clupsUserDetailsService.addUser("admin", "admin");
        }
    }

    private void seedCategory() {
        if (categoryService.getAll().size() == 0) {
            categoryService.addNew(new Category("Snacks"));
            categoryService.addNew(new Category("Beer"));
            categoryService.addNew(new Category("Wine"));
            categoryService.addNew(new Category("Warm beverages"));
            categoryService.addNew(new Category("Soft drinks"));
        }
    }

    private void seedProduct() {
        if (productService.getAll().size() == 0) {
            productService.addNew(new Product("Heineken", BigDecimal.valueOf(1.20)));
            productService.addNew(new Product("Grolsch", BigDecimal.valueOf(1.40)));
        }
    }

}
