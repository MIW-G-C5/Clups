package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.OrderDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.dtos.StateKeeper;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This class controls the main page of the application
 */

@Controller
@SessionAttributes("stateKeeper")
public class MainPageController {

    public final ProductService productService;
    public final CategoryService categoryService;

    @Autowired
    public MainPageController(ProductService products, CategoryService categories) {
        this.productService = products;
        this.categoryService = categories;
    }

    @ModelAttribute("stateKeeper")
    public StateKeeper stateKeeper(){
        return new StateKeeper();
    }

    @GetMapping({"/"})
    protected String showPage(Model model, @ModelAttribute("stateKeeper") StateKeeper stateKeeper) {
        createOrder(stateKeeper);

        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute
                ("allProductsByCategory", productService.getProductsByCategory(stateKeeper.getCategoryName()));
        model.addAttribute("orderList", stateKeeper.getOrder().getOrderedItems());
        model.addAttribute("orderTotal", stateKeeper.getOrder().calculateTotalCostOrder());
        return "mainPage";
    }

    private void createOrder(StateKeeper stateKeeper) {
        if (stateKeeper.getOrder() == null) {
            stateKeeper.setOrder(new OrderDto());
        }
    }

    @GetMapping({"/selectCategory/{categoryName}"})
    protected String showProductsByCategory(
            @PathVariable("categoryName") String categoryName,
            @SessionAttribute("stateKeeper") StateKeeper stateKeeper) {
        stateKeeper.setCategoryName(categoryName);
        return "redirect:/";
    }

    @GetMapping({"/order/{productName}"})
    protected String addProductToOrder(@PathVariable("productName") String productName,
                                       @SessionAttribute("stateKeeper") StateKeeper stateKeeper) {
        ProductDto orderedProduct = productService.findProductByName(productName);
        stateKeeper.getOrder().addToOrder(orderedProduct);
        return "redirect:/";
    }

    @GetMapping({"/order/remove/{productName}"})
    protected String removeProductFromOrder(@PathVariable("productName") String productName,
                                            @SessionAttribute("stateKeeper") StateKeeper stateKeeper) {
        ProductDto orderedProduct = productService.findProductByName(productName);
        stateKeeper.getOrder().removeFromOrder(orderedProduct);
        return "redirect:/";
    }

    @GetMapping({"/order/clear"})
    protected String clearOrder(@SessionAttribute("stateKeeper") StateKeeper stateKeeper) {
        stateKeeper.getOrder().emptyOrder();
        return "redirect:/";
    }



}
