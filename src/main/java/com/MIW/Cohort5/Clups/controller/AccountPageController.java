package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.AccountPageStateKeeper;
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
@SessionAttributes("accountPageStateKeeper")
public class AccountPageController {

    private final UserService userService;

    @Autowired
    public AccountPageController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("accountPageStateKeeper")
    public AccountPageStateKeeper accountPageStateKeeper(){
        return new AccountPageStateKeeper();
    }

    @GetMapping({"/accounts"})
    protected String showPage(Model model,
                              @ModelAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {
        model.addAttribute("selectedPage", "userPage");
        model.addAttribute("allUsers", userService.getAll());

        if (stateKeeper.getUserDto() == null) {
            stateKeeper.setUserDto(new UserDto());
        }

        model.addAttribute("user", stateKeeper.getUserDto());
        model.addAttribute("formState", stateKeeper.isShowForm());
        return "userAccountPage";
    }

    @GetMapping({"/accounts/addNew"})
    protected String addNewUser(
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        stateKeeper.setShowForm(true);

        return "redirect:/accounts";
    }

    @PostMapping({"/accounts/addNew"})
    protected String saveNewUser(
            @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        if(!result.hasErrors()) {
            stateKeeper.setUserDto(userDto);

            userService.saveUser(stateKeeper.getUserDto());

            clearForm(stateKeeper);
        }

        return "redirect:/accounts";
    }

    @GetMapping({"/accounts/cancel"})
    protected String cancelForm(
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        stateKeeper.setShowForm(false);

        return "redirect:/accounts";
    }


    private void clearForm(AccountPageStateKeeper stateKeeper) {
        stateKeeper.clearUser();
        stateKeeper.setShowForm(false);
    }

}
