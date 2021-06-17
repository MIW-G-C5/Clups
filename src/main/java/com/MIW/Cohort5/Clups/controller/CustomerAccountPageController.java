package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.UserPageStateKeeper;
import com.MIW.Cohort5.Clups.services.UserService;
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

    private final UserService userService;

    @Autowired
    public CustomerAccountPageController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("customerPageStateKeeper")
    public UserPageStateKeeper userPageStateKeeper(){
        return new UserPageStateKeeper();
    }

    @GetMapping({"/customers"})
    protected String showPage(Model model,
                              @ModelAttribute("customerPageStateKeeper") UserPageStateKeeper stateKeeper) {
        model.addAttribute("selectedPage", "customerPage");
        model.addAttribute("allCustomers", userService.getAll());

        if (stateKeeper.getUserDto() == null) {
            stateKeeper.setUserDto(new UserDto());
        }

        model.addAttribute("customer", stateKeeper.getUserDto());
        model.addAttribute("formState", stateKeeper.isShowForm());
        return "customerAccountPage";
    }

    @GetMapping({"/customers/addNew"})
    protected String addNewCustomer(
            @SessionAttribute("customerPageStateKeeper") UserPageStateKeeper stateKeeper) {

        stateKeeper.setShowForm(true);

        return "redirect:/customers";
    }

    @PostMapping({"/customers/addNew"})
    protected String saveNewCustomer(
            @ModelAttribute("customer") UserDto userDto,
            BindingResult result,
            @SessionAttribute("customerPageStateKeeper") UserPageStateKeeper stateKeeper) {

        if(!result.hasErrors()) {
            stateKeeper.setUserDto(userDto);

            userService.saveUser(stateKeeper.getUserDto());

            clearForm(stateKeeper);
        }

        return "redirect:/customers";
    }

    @GetMapping({"/customers/cancel"})
    protected String cancelForm(
            @SessionAttribute("customerPageStateKeeper") UserPageStateKeeper stateKeeper) {

        stateKeeper.setShowForm(false);

        return "redirect:/customers";
    }

    private void clearForm(UserPageStateKeeper stateKeeper) {
        stateKeeper.clearCustomer();
        stateKeeper.setShowForm(false);
    }

}
