package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.OrderDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.MainPageStateKeeper;
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
@SessionAttributes("mainPageStateKeeper")
public class MainPageController {

    public final ProductService productService;
    public final CategoryService categoryService;

    @Autowired
    public MainPageController(ProductService products, CategoryService categories) {
        this.productService = products;
        this.categoryService = categories;
    }

    @ModelAttribute("mainPageStateKeeper")
    public MainPageStateKeeper stateKeeper(){
        return new MainPageStateKeeper();
    }

    @GetMapping({"/"})
    protected String showPage(Model model, @ModelAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {
        createOrder(mainPageStateKeeper);

        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute
                ("allProductsByCategory", productService.getProductsByCategory(mainPageStateKeeper.getCategoryName()));
        model.addAttribute("orderList", mainPageStateKeeper.getOrder().getOrderedItems());
        model.addAttribute("orderTotal", mainPageStateKeeper.getOrder().calculateTotalCostOrder());
        model.addAttribute("selectedPage", "mainPage");
        return "mainPage";
    }

    private void createOrder(MainPageStateKeeper mainPageStateKeeper) {
        if (mainPageStateKeeper.getOrder() == null) {
            mainPageStateKeeper.setOrder(new OrderDto());
        }
    }

    @GetMapping({"/selectCategory/{categoryName}"})
    protected String showProductsByCategory(
            @PathVariable("categoryName") String categoryName,
            @SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {
        mainPageStateKeeper.setCategoryName(categoryName);
        return "redirect:/";
    }

    @GetMapping({"/order/{productName}"})
    protected String addProductToOrder(@PathVariable("productName") String productName,
                                       @SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {
        ProductDto orderedProduct = productService.findProductByName(productName);
        mainPageStateKeeper.getOrder().addToOrder(orderedProduct);
        return "redirect:/";
    }

    @GetMapping({"/order/remove/{productName}"})
    protected String removeProductFromOrder(@PathVariable("productName") String productName,
                                            @SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {
        ProductDto orderedProduct = productService.findProductByName(productName);
        mainPageStateKeeper.getOrder().removeFromOrder(orderedProduct);
        return "redirect:/";
    }

    @GetMapping({"/order/clear"})
    protected String clearOrder(@SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {
        mainPageStateKeeper.getOrder().emptyOrder();
        return "redirect:/";
    }



}
