package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.OrderDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
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
 *
 * This class controls the main page of the application
 */

@Controller
public class MainPageController {

    public final ProductService productService;
    public final CategoryService categoryService;

    private OrderDto order;
    private String selectedCategoryName;

    @Autowired
    public MainPageController(ProductService products, CategoryService categories) {
        this.productService = products;
        this.categoryService = categories;
    }

    @GetMapping({"/"})
    protected String showPage(Model model) {
        createOrder();

        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute("allProductsByCategory", productService.getProductsByCategory(selectedCategoryName)); //todo servicelayer
        model.addAttribute("orderList", order.getOrderedItems());
        model.addAttribute("orderTotal", order.calculateTotalCostOrder());
        return "mainPage";
    }

    @GetMapping({"/productOverview/{categoryName}"})
    protected String showProductsByCategory(
            @PathVariable("categoryName") String categoryName) {
        selectedCategoryName = categoryName;
        return "redirect:/";
    }

    @GetMapping({"/order/{productName}"})
    protected String addProductToOrder(@PathVariable("productName") String productName) {
        ProductDto orderedProduct = productService.findProductByName(productName); //todo servicelayer
        order.addToOrder(orderedProduct);
        return "redirect:/";
    }

    @GetMapping({"/order/remove/{productName}"})
    protected String removeProductFromOrder(@PathVariable("productName") String productName) {
        ProductDto orderedProduct = productService.findProductByName(productName);
        order.removeFromOrder(orderedProduct);
        return "redirect:/";
    }

    private void createOrder() {
        if (order == null) {
            order = new OrderDto();
        }
    }







}
