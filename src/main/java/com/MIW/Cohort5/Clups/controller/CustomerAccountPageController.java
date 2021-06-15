package com.MIW.Cohort5.Clups.controller;

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

    @GetMapping({"/customers"})
    protected String showPage(Model model) {
        model.addAttribute("selectedPage", "customerPage");

        return "customerAccountPage";
    }

}
