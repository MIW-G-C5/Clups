package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Johnnie Meijer
 * j.j.meijer@st.hanze.nl
 *
 * this class controls product views
 */

@Controller
public class ProductController {

    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping({"/", "/products"})
    protected String showProducts(Model model) {
        model.addAttribute("allProducts", productRepository.findAll());
        return "mainPage";
    }

}
