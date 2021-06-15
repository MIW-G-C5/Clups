package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This controller handles all actions for customer accounts in the application
 */
@Controller
public class CustomerAccountPageController {

    private final CustomerService customerService;

    public CustomerAccountPageController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/customers"})
    protected String showPage(Model model) {
        model.addAttribute("selectedPage", "customerPage");

        model.addAttribute("allCustomers", customerService.getAll());

        return "customerAccountPage";
    }

}
