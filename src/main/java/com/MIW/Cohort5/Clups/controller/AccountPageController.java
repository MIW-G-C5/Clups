package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.AccountPageStateKeeper;
import com.MIW.Cohort5.Clups.model.User;
import com.MIW.Cohort5.Clups.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
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

    // Empty String fields will be considered NULL instead of an empty String
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmer);
    }

    private final UserService userService;

    @Autowired
    public AccountPageController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("accountPageStateKeeper")
    public AccountPageStateKeeper accountPageStateKeeper(){
        return new AccountPageStateKeeper();
    }

    @GetMapping("/accounts")
    protected String showPage(Model model,
                              @ModelAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {
        model.addAttribute("selectedPage", "userPage");
        model.addAttribute("allUsers", userService.getAll());

        if (stateKeeper.getCurrentUserDto() == null) {
            stateKeeper.setCurrentUserDto(new UserDto());
        }

        model.addAttribute("user", stateKeeper.getCurrentUserDto());
        model.addAttribute("processStep", stateKeeper.getProcessStep());
        return "userAccountPage";
    }

    @GetMapping("/accounts/addNew")
    protected String addNewUser(
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        stateKeeper.processAdduser();

        return "redirect:/accounts";
    }

    @PostMapping("/accounts/addNew")
    protected String saveNewUser(
            @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        if(!result.hasErrors()) {
            stateKeeper.setCurrentUserDto(userDto);

            if (userDto.getFullName() == null || userDto.getFullName() == "") {
                throw new InputMismatchException("The full name must be filled in");
            }

            userService.newUser(stateKeeper.getCurrentUserDto());

            clearForm(stateKeeper);
        }

        return "redirect:/accounts";
    }

    @GetMapping("/accounts/{userCode}")
    protected String selectUser(
            @PathVariable("userCode") String userCode,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper) {
        accountPageStateKeeper.setCurrentUserDto(userService.findDtoByUserCode(Integer.parseInt(userCode)));
        accountPageStateKeeper.processUserSelected();

        return "redirect:/accounts";
    }

    @GetMapping("/accounts/editUser")
    protected String editUser(@SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {
        stateKeeper.processEditUser();

        return "redirect:/accounts";
    }

    @PostMapping("/accounts/edit")
    protected String saveExistingUser(@ModelAttribute("user") UserDto userDto,
                                      BindingResult result,
                                      @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper){
        if (!result.hasErrors()){
            accountPageStateKeeper.setCurrentUserDto(userDto);
            userService.editUser(accountPageStateKeeper.getCurrentUserDto());

            clearForm(accountPageStateKeeper);
        }
        return "redirect:/accounts";
    }

    @GetMapping("accounts/delete")
    protected String deleteUser(@SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper){

        userService.deleteUser(accountPageStateKeeper.getCurrentUserDto());
        clearForm(accountPageStateKeeper);
        accountPageStateKeeper.clearUser();

        return "redirect:/accounts";
    }


    @GetMapping("/accounts/cancel")
    protected String cancelForm(
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        clearForm(stateKeeper);

        return "redirect:/accounts";
    }

    private void clearForm(AccountPageStateKeeper stateKeeper) {
        stateKeeper.clearUser();
    }

    @GetMapping("/accounts/addCredit")
    protected String showAddCreditForm(@SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {
        stateKeeper.processAddCredit();

        return "redirect:/accounts";
    }

    @PostMapping("/accounts/addCredit")
    protected String addCredit(@ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               @RequestParam("credit") Integer credit,
                               @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper){

        if (!result.hasErrors()) {
            UserDto existingUser = userService.findDtoByUserCode(userDto.getUserCode());

            existingUser.addToBalance(credit);

            // this prevents addCredit from resetting a password
            existingUser.setPassword(null);

            accountPageStateKeeper.setCurrentUserDto(existingUser);

            userService.editUser(accountPageStateKeeper.getCurrentUserDto());

            accountPageStateKeeper.processUserSelected();
        }

        return "redirect:/accounts";
    }

    @GetMapping("/accounts/close")
    protected String closeEditUser(@SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {
        stateKeeper.processUserSelected();

        return "redirect:/accounts";
    }


}
