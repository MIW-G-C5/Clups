package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.CategoryDto;
import com.MIW.Cohort5.Clups.dtos.OrderDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This class controls the main page of the application
 */

@Controller
public class MainPageController {

    public final ProductService productService;
    public final CategoryService categoryService;

    private OrderDto order;

    @Autowired
    public MainPageController(ProductService products, CategoryService categories) {
        this.productService = products;
        this.categoryService = categories;
    }

    @GetMapping({"/"})
    protected String showPage(Model model) {
        model.addAttribute("allCategories", categoryService.getAll());
        //todo weghalen?
//        model.addAttribute("allProductsByCategory", ???);
        if (order == null) {
            order = new OrderDto();
        }
        model.addAttribute("orderList", order.getOrderedItems());
        model.addAttribute("orderTotal", order.calculateTotalCostOrder());
        return "mainPage";
    }

    @GetMapping({"/productOverview/{categoryName}"})
    protected String showProductsByCategory(
            @PathVariable("categoryName") String categoryName, Model model) {
        CategoryDto categoryDto = findByCategoryName(categoryName);
        model.addAttribute("allProductsByCategory", categoryDto.getProducts());
        return "redirect:/";
    }

    @GetMapping({"/order/{productName}"})
    protected String addProductToOrder(@PathVariable("productName") String productName) {
        ProductDto orderedProduct = findByName(productName);
        order.addToOrder(orderedProduct);
        return "redirect:/";
    }

    private ProductDto findByName(String name) {
        List<ProductDto> allProducts = productService.getAll();

        ProductDto productByName = null;
        for (ProductDto product : allProducts) {
            if (product.getProductName().equals(name)) {
                productByName = product;
            }
        }

        return productByName;
    }

    private CategoryDto findByCategoryName(String name) {
        List<CategoryDto> allCategories = categoryService.getAll();

        CategoryDto categoryByName = null;
        for (CategoryDto category : allCategories) {
            if (category.getCategoryName().equals(name)) {
                categoryByName = category;
            }
        }

        return categoryByName;
    }

}
