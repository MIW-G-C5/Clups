package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.AccountPageStateKeeper;
import com.MIW.Cohort5.Clups.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.InputMismatchException;

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

        if (stateKeeper.getCurrentUserDto() == null) {
            stateKeeper.setCurrentUserDto(new UserDto());
        }

        model.addAttribute("user", stateKeeper.getCurrentUserDto());
        model.addAttribute("formState", stateKeeper.isShowUserForm());
        return "userAccountPage";
    }

    @GetMapping({"/accounts/addNew"})
    protected String addNewUser(
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        stateKeeper.setShowUserForm(true);

        return "redirect:/accounts";
    }

    @PostMapping({"/accounts/addNew"})
    protected String saveNewUser(
            @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        if(!result.hasErrors()) {
            stateKeeper.setCurrentUserDto(userDto);

            if (userDto.getFullName() == null || userDto.getFullName() == "") {
                throw new InputMismatchException("The full name must be filled in");
            }

            userService.saveUser(stateKeeper.getCurrentUserDto());

            clearForm(stateKeeper);
            stateKeeper.setShowUserForm(false);
        }

        return "redirect:/accounts";
    }

    @GetMapping({"/accounts/{userCode}"})
    protected String editUser(
            @PathVariable("userCode") String userCode,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper){

        accountPageStateKeeper.setCurrentUserDto( userService.findDtoByUserCode(Integer.parseInt(userCode)));
        showUserForm(accountPageStateKeeper);
        return "redirect:/accounts";
    }

    @PostMapping("/accounts/edit")
    protected String saveExistingUser(@ModelAttribute("user") UserDto userDto,
                                      BindingResult result,
                                      @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper){
        if (!result.hasErrors()){
            accountPageStateKeeper.setShowUserForm(true);
            accountPageStateKeeper.setCurrentUserDto(userDto);
            userService.saveUser(accountPageStateKeeper.getCurrentUserDto());

            clearForm(accountPageStateKeeper);
            accountPageStateKeeper.setShowUserForm(false);
        }
        return "redirect:/accounts";
    }

   private void showUserForm(@SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper){
        if (!accountPageStateKeeper.isShowUserForm()){
            accountPageStateKeeper.setShowUserForm(true);
        }
   }

    @GetMapping({"/accounts/cancel"})
    protected String cancelForm(
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        stateKeeper.setShowUserForm(false);

        return "redirect:/accounts";
    }

    private void clearForm(AccountPageStateKeeper stateKeeper) {
        stateKeeper.clearUser();
        stateKeeper.setShowUserForm(false);
    }

}
