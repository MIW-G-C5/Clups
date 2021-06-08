package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.OrderDto;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 */

@Controller
public class ProductPageController {

    public final ProductService productService;
    public final CategoryService categoryService;

    private String selectedCategoryName;

    @Autowired
    public ProductPageController(ProductService products, CategoryService categories) {
        this.productService = products;
        this.categoryService = categories;
    }

    @GetMapping({"/products"})
    protected String showPage(Model model) {
        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute("allProductsByCategory", productService.getProductsByCategory(selectedCategoryName));
        return "productEditor";
    }

    @GetMapping({"/products/selectCategory/{categoryName}"})
    protected String showProductsByCategory(
            @PathVariable("categoryName") String categoryName) {
        selectedCategoryName = categoryName;
        return "redirect:/products";
    }

}
