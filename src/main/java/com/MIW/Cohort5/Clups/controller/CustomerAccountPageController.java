package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.CustomerDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.CustomerAccountPageStateKeeper;
import com.MIW.Cohort5.Clups.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 *
 * This controller handles all actions for customer accounts in the application
 */
@Controller
@SessionAttributes("customerPageStateKeeper")
public class CustomerAccountPageController {

    private final CustomerService customerService;

    @Autowired
    public CustomerAccountPageController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ModelAttribute("customerPageStateKeeper")
    public CustomerAccountPageStateKeeper customerPageStateKeeper(){
        return new CustomerAccountPageStateKeeper();
    }

    @GetMapping({"/customers"})
    protected String showPage(Model model,
                              @ModelAttribute("customerPageStateKeeper") CustomerAccountPageStateKeeper stateKeeper) {
        model.addAttribute("selectedPage", "customerPage");
        model.addAttribute("allCustomers", customerService.getAll());

        if (stateKeeper.getCurrentCustomer() == null) {
            stateKeeper.setCurrentCustomer(new CustomerDto());
        }

        model.addAttribute("customer", stateKeeper.getCurrentCustomer());
        model.addAttribute("formState", stateKeeper.isShowForm());
        return "customerAccountPage";
    }

    @GetMapping({"/customers/addNew"})
    protected String addNewCustomer(
            @SessionAttribute("customerPageStateKeeper") CustomerAccountPageStateKeeper stateKeeper) {

        stateKeeper.setShowForm(true);

        return "redirect:/customers";
    }

    @PostMapping({"/customers/addNew"})
    protected String saveNewCustomer(
            @ModelAttribute("customer") CustomerDto customerDto,
            BindingResult result,
            @SessionAttribute("customerPageStateKeeper") CustomerAccountPageStateKeeper stateKeeper) {

        if(!result.hasErrors()) {
            stateKeeper.setCurrentCustomer(customerDto);

            customerService.saveCustomer(stateKeeper.getCurrentCustomer());

            clearForm(stateKeeper);
        }

        return "redirect:/customers";
    }

    @GetMapping({"/customers/cancel"})
    protected String cancelForm(
            @SessionAttribute("customerPageStateKeeper") CustomerAccountPageStateKeeper stateKeeper) {

        stateKeeper.setShowForm(false);

        return "redirect:/customers";
    }


    private void clearForm(CustomerAccountPageStateKeeper stateKeeper) {
        stateKeeper.clearCustomer();
        stateKeeper.setShowForm(false);
    }


}
