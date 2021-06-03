package com.MIW.Cohort5.Clups.seeding;

import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.repository.CategoryRepository;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import com.MIW.Cohort5.Clups.services.implementations.ClupsUserDetailsServiceImpl;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author S.K.C.Reijntjes
 * <p>
 * This class adds testdata to the database
 */

@Component
public class Seeder {

    private ProductService productService;
    private CategoryService categoryService;
    private ClupsUserDetailsServiceImpl clupsUserDetailsService;
    private CategoryRepository categoryRepository;

    public Seeder(ProductService productService, ClupsUserDetailsServiceImpl clupsUserDetailsService,
                  CategoryService categoryService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.clupsUserDetailsService = clupsUserDetailsService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }

    // This generates the test data when launching the program.
    // The database must be reset manually by clearing all data in MySQL.
    // The event variable is grey because it only exists when running the program.
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedUser();
        seedCategory();
        seedProduct();
    }

    private void seedUser() {
        if (clupsUserDetailsService.getAll().size() == 0) {
            clupsUserDetailsService.addUser("admin", "admin");
        }
    }

    private void seedCategory() {
        if (categoryService.getAll().size() == 0) {
            categoryService.addNew(new Category("Snacks", 1));
            categoryService.addNew(new Category("Beer", 2));
            categoryService.addNew(new Category("Wine", 3));
            categoryService.addNew(new Category("Warm beverages", 4));
            categoryService.addNew(new Category("Soft drinks", 5));
            categoryService.addNew(new Category("Additions", 6));
        }
    }

    private void seedProduct() {
        if (productService.getAll().size() == 0) {
            productService.addNew(new Product("Nuts", BigDecimal.valueOf(1), findCategory(1)));
            productService.addNew(new Product("Bitterballllls", BigDecimal.valueOf(2.50), findCategory(1)));
            productService.addNew(new Product("Chips", BigDecimal.valueOf(2), findCategory(1)));
            productService.addNew(new Product("Heineken", BigDecimal.valueOf(2.00), findCategory(2)));
            productService.addNew(new Product("Grolsch", BigDecimal.valueOf(2.00), findCategory(2)));
            productService.addNew(new Product("Red wine", BigDecimal.valueOf(2.50), findCategory(3)));
            productService.addNew(new Product("White wine", BigDecimal.valueOf(2.50), findCategory(3)));
            productService.addNew(new Product("Tea", BigDecimal.valueOf(1.50), findCategory(4)));
            productService.addNew(new Product("Coffee", BigDecimal.valueOf(1.50), findCategory(4)));
            productService.addNew(new Product("Hot choc", BigDecimal.valueOf(2), findCategory(4)));
            productService.addNew(new Product("Coke", BigDecimal.valueOf(1.50), findCategory(5)));
            productService.addNew(new Product("Lemonade", BigDecimal.valueOf(0.50), findCategory(5)));
            productService.addNew(new Product("Whipped Cream", BigDecimal.valueOf(1), findCategory(6)));
            productService.addNew(new Product("Mayonnaise", BigDecimal.valueOf(0.25), findCategory(6)));

        }
    }

    public Category findCategory(int categoryCode) {
        return categoryRepository.findCategoryByCategoryCode(categoryCode);
    }

}
