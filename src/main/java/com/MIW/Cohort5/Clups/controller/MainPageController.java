package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.OrderDto;
import com.MIW.Cohort5.Clups.dtos.ProductDto;
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
    private OrderDto order;

    @Autowired
    public MainPageController(ProductService products) {
        this.productService = products;
    }

    @GetMapping({"/"})
    protected String showPage(Model model) {
        model.addAttribute("allProducts", productService.getAll());
        if (order == null) {
            order = new OrderDto();
        }
        model.addAttribute("orderList", order.getOrderedItems());
        return "mainPage";
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

}