package com.MIW.Cohort5.Clups.seeding;

import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.services.ProductService;
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

    public Seeder(ProductService productService) {
        this.productService = productService;
    }

    // This generates the test data when launching the program.
    // The database must be reset manually by clearing all data in MySQL.
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedProduct();
    }

    private void seedProduct() {
        if (productService.getAll().size() == 0) {
            productService.addNew(new Product("Heineken", BigDecimal.valueOf(1.20)));
            productService.addNew(new Product("Grolsch", BigDecimal.valueOf(1.40)));

        }
    }

}
