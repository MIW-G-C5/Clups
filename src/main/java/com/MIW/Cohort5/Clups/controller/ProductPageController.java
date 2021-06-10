package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.ProductPageStateKeeper;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 */

@Controller
@SessionAttributes("productPageStateKeeper")
public class ProductPageController {

    public final ProductService productService;
    public final CategoryService categoryService;

    @Autowired
    public ProductPageController(ProductService products, CategoryService categories) {
        this.productService = products;
        this.categoryService = categories;
    }

    @ModelAttribute("productPageStateKeeper")
    public ProductPageStateKeeper productPageStateKeeper() {
        return new ProductPageStateKeeper();
    }

    @GetMapping({"/products"})
    protected String showPage(Model model,
                              @ModelAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {
        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute("allProductsByCategory",
                productService.getProductsByCategory(productPageStateKeeper.getCategoryName()));

        if (productPageStateKeeper.getCurrentProduct() == null) {
            productPageStateKeeper.setCurrentProduct(new ProductDto());
        }

        model.addAttribute("product", productPageStateKeeper.getCurrentProduct());

        return "productEditor";
    }

    @GetMapping({"/products/selectCategory/{categoryName}"})
    protected String showProductsByCategory(
            @PathVariable("categoryName") String categoryName,
            @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {
        productPageStateKeeper.setCategoryName(categoryName);
        return "redirect:/products";
    }

    @GetMapping({"/products/addNew"})
    protected String addNewProduct(Model model) {
        model.addAttribute("allCategoryNames", categoryService.getAll());

        return "redirect:/products";
    }

    @PostMapping({"/products/addNew"})
    protected String saveProduct(@ModelAttribute("product") ProductDto productDto,
                                 BindingResult result,
                                 @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {
        if (!result.hasErrors()) {
            productPageStateKeeper.setCurrentProduct(productDto);
            System.out.println(productPageStateKeeper.getCurrentProduct().getProductName()
                    + " "
                    + productPageStateKeeper.getCurrentProduct().getProductPrice()
                    + " "
                    + productPageStateKeeper.getCurrentProduct().getCategoryName());
            productPageStateKeeper.clearCurrentProduct();
        }

        return "redirect:/products";
    }

}
