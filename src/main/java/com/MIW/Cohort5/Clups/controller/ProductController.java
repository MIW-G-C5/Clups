package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * This class controls product views
 */

@Controller
public class ProductController {

    public final ProductService productService;

    @Autowired
    public ProductController(ProductService products) {
        this.productService = products;
    }

    @GetMapping({"/", "/products"})
    protected String showProducts(Model model) {
        model.addAttribute("allProducts", productService.getAll());
        return "productOverview";
    }

}
