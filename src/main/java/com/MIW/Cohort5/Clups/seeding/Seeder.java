package com.MIW.Cohort5.Clups.seeding;

import com.MIW.Cohort5.Clups.model.Category;
import com.MIW.Cohort5.Clups.model.Product;
import com.MIW.Cohort5.Clups.model.Role;
import com.MIW.Cohort5.Clups.model.User;
import com.MIW.Cohort5.Clups.repository.CategoryRepository;
import com.MIW.Cohort5.Clups.repository.RoleRepository;
import com.MIW.Cohort5.Clups.repository.UserRepository;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.UserService;
import com.MIW.Cohort5.Clups.services.ProductService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

/**
 * @author S.K.C.Reijntjes
 * <p>
 * This class adds testdata to the database.
 */

@Component
public class Seeder {

    private ProductService productService;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private UserService userService;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public Seeder(ProductService productService,
                  CategoryService categoryService,
                  CategoryRepository categoryRepository,
                  UserService userService,
                  UserRepository userRepository,
                  RoleRepository roleRepository) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // This generates the test data when launching the program.
    // The database must be reset manually by clearing all data in MySQL.
    // The event variable is grey because it only exists when running the program.
    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedRoles();
        seedUsers();
        seedCategory();
        seedProduct();
    }

    private void seedRoles() {
        if (roleRepository.findAll().size() == 0) {
            Role barmanager = new Role("BARMANAGER");
            roleRepository.save(barmanager);

            Role bartender = new Role("BARTENDER");
            roleRepository.save(bartender);

            Role customer = new Role("CUSTOMER");
            roleRepository.save(customer);
        }
    }

    private void seedUsers() {
        if (userRepository.findAll().size() == 0) {

            Role bartender = roleRepository.findRoleByRoleName("BARTENDER");
            Role customer = roleRepository.findRoleByRoleName("CUSTOMER");
            Role barmanager = roleRepository.findRoleByRoleName("BARMANAGER");

            userService.addUser(new User("petra",
                                         "petra",
                                         "Petra Young", barmanager, BigDecimal.valueOf(0)));
            userService.addUser(new User("steve",
                                         "steve",
                                         "Steve Martin", bartender, BigDecimal.valueOf(2.5)));
            userService.addUser(new User("george",
                                         "george",
                                         "George Anderson", customer, BigDecimal.valueOf(50)));
            userService.addUser(new User("mary",
                                         "mary",
                                         "Mary Smith", customer, BigDecimal.valueOf(10)));
            userService.addUser(new User("carl",
                                         "carl",
                                         "Carl Williams", barmanager, BigDecimal.valueOf(20)));
            userService.addUser(new User("lisa",
                                         "lisa",
                                         "Lisa Lewis", customer, BigDecimal.valueOf(12.5)));
            userService.addUser(new User("james",
                                         "james",
                                         "James Smith", customer, BigDecimal.valueOf(7.5)));
            userService.addUser(new User("maria",
                                         "maria",
                                         "Maria Garcia", bartender, BigDecimal.valueOf(0)));
            userService.addUser(new User("arnold",
                                         "arnold",
                                         "Arnold Jones", customer, BigDecimal.valueOf(15.25)));
            userService.addUser(new User("jennifer",
                                         "jennifer",
                                         "Jennifer Taylor", customer, BigDecimal.valueOf(0)));
            userService.addUser(new User("mark",
                                         "mark",
                                         "Mark Moore", customer, BigDecimal.valueOf(13.33)));
            userService.addUser(new User("sarah",
                                         "sarah",
                                         "Sarah Lee", customer, BigDecimal.valueOf(13.33)));
        }
    }

    private void seedCategory() {
        if (categoryService.getAll().size() == 0) {
            categoryService.addNew(new Category("Snacks", 1));
            categoryService.addNew(new Category("Pie", 7));
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
            productService.addNew(new Product("Bitterballen", BigDecimal.valueOf(2.50), findCategory(1)));
            productService.addNew(new Product("Potato chips", BigDecimal.valueOf(2), findCategory(1)));
            productService.addNew(new Product("Fries", BigDecimal.valueOf(4.25), findCategory(1)));
            productService.addNew(new Product("Grilled cheese sandwich", BigDecimal.valueOf(3), findCategory(1)));


            productService.addNew(new Product("Apple Pie", BigDecimal.valueOf(4), findCategory(7)));

            productService.addNew(new Product("Heineken", BigDecimal.valueOf(2.00), findCategory(2)));
            productService.addNew(new Product("Grolsch", BigDecimal.valueOf(2.00), findCategory(2)));
            productService.addNew(new Product("Amstel", BigDecimal.valueOf(1.60), findCategory(2)));
            productService.addNew(new Product("Hertog Jan", BigDecimal.valueOf(2.60), findCategory(2)));

            productService.addNew(new Product("Red wine", BigDecimal.valueOf(2.50), findCategory(3)));
            productService.addNew(new Product("White wine", BigDecimal.valueOf(2.50), findCategory(3)));
            productService.addNew(new Product("Rosé", BigDecimal.valueOf(2.80), findCategory(3)));

            productService.addNew(new Product("Tea", BigDecimal.valueOf(1.50), findCategory(4)));
            productService.addNew(new Product("Coffee", BigDecimal.valueOf(1.50), findCategory(4)));
            productService.addNew(new Product("Hot chocolate", BigDecimal.valueOf(2), findCategory(4)));

            productService.addNew(new Product("Coke", BigDecimal.valueOf(1.50), findCategory(5)));
            productService.addNew(new Product("Fanta", BigDecimal.valueOf(1.50), findCategory(5)));
            productService.addNew(new Product("Sprite", BigDecimal.valueOf(1.50), findCategory(5)));
            productService.addNew(new Product("Lemonade", BigDecimal.valueOf(0.50), findCategory(5)));

            productService.addNew(new Product("Whipped Cream", BigDecimal.valueOf(1), findCategory(6)));
            productService.addNew(new Product("Mayonnaise", BigDecimal.valueOf(0.25), findCategory(6)));
            productService.addNew(new Product("Ketchup", BigDecimal.valueOf(0.25), findCategory(6)));

        }
    }

    public Category findCategory(int categoryCode) {
        return categoryRepository.findCategoryByCategoryCode(categoryCode);
    }

}
