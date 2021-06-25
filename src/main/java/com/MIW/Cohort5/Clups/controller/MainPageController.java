package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.OrderDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.MainPageStateKeeper;
import com.MIW.Cohort5.Clups.services.CategoryService;
import com.MIW.Cohort5.Clups.services.ProductService;
import com.MIW.Cohort5.Clups.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public final UserService userService;

    @Autowired
    public MainPageController(ProductService products, CategoryService categories, UserService users) {
        this.productService = products;
        this.categoryService = categories;
        this.userService = users;
    }

    @ModelAttribute("mainPageStateKeeper")
    public MainPageStateKeeper stateKeeper(){
        return new MainPageStateKeeper();
    }

    @GetMapping({"/"})
    protected String enterApplication() {

        return "redirect:/order";
    }

    @GetMapping({"/order"})
    protected String showPage(
            Model model,
            @ModelAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {

        createOrder(mainPageStateKeeper);
        model.addAttribute("allCategories", categoryService.getAll());
        model.addAttribute("allProductsByCategory",
                              productService.getProductsByCategory(mainPageStateKeeper.getCategoryCode()));
        model.addAttribute("orderList", mainPageStateKeeper.getOrder().getOrderedItems());
        model.addAttribute("orderTotal", mainPageStateKeeper.getOrder().calculateTotalCostOrder());
        model.addAttribute("selectedPage", "mainPage");

        model.addAttribute("userList", mainPageStateKeeper.getSortedUsers());
        return "mainPage";
    }

    private void createOrder(MainPageStateKeeper mainPageStateKeeper) {
        if (mainPageStateKeeper.getOrder() == null) {
            mainPageStateKeeper.setOrder(new OrderDto());
        }
    }

    @GetMapping({"/selectCategory/{categoryCode}"})
    protected String showProductsByCategory(
            @PathVariable("categoryCode") String categoryCodeString,
            @SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {
        mainPageStateKeeper.setCategoryCode(Integer.parseInt(categoryCodeString));
        return "redirect:/order";
    }

    @GetMapping({"/order/{productCode}"})
    protected String addProductToOrder(@PathVariable("productCode") String productCodeString,
                                       @SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {
        ProductDto orderedProduct = productService.findDtoByCode(Integer.parseInt(productCodeString));
        mainPageStateKeeper.getOrder().addToOrder(orderedProduct);
        return "redirect:/order";
    }

    @GetMapping({"/order/remove/{productCode}"})
    protected String removeProductFromOrder(
            @PathVariable("productCode") String productCodeString,
            @SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {

        ProductDto orderedProduct = productService.findDtoByCode(Integer.parseInt(productCodeString));
        mainPageStateKeeper.getOrder().removeFromOrder(orderedProduct);
        return "redirect:/order";
    }

    @GetMapping({"/order/clear"})
    protected String clearOrder(@SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper) {
        mainPageStateKeeper.getOrder().emptyOrder();
        return "redirect:/order";
    }

    @RequestMapping("/403")
    public String accessDenied() {
        return "error/403";
    }

    @GetMapping({"/users/search"})
    protected String searchUser(@SessionAttribute("mainPageStateKeeper") MainPageStateKeeper mainPageStateKeeper,
                                Model model,
                                @RequestParam String request) {
        List<Integer> searchResults = userService.getUserByPartialString(request);
        List<UserDto> sortedUsers = new ArrayList<>();

        for (Integer searchResult : searchResults) {
            sortedUsers.add(userService.findDtoByUserCode(searchResult));
        }

        mainPageStateKeeper.setSortedUsers(sortedUsers);

        model.addAttribute("userList", mainPageStateKeeper.getSortedUsers());

        return "redirect:/order";
    }


}
