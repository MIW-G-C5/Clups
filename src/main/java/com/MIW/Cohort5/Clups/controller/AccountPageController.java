package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.AccountPageStateKeeper;
import com.MIW.Cohort5.Clups.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;

/**
 * @author Kimberley Hommes - k.hommes@st.hanze.nl
 * <p>
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
    public AccountPageStateKeeper accountPageStateKeeper() {
        return new AccountPageStateKeeper();
    }

    @GetMapping("/accounts")
    protected String showPage(Model model,
                              @ModelAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {
        model.addAttribute("selectedPage", "userPage");

        List<UserDto> allUsers = userService.getAll();
        Collections.sort(allUsers);
        model.addAttribute("allUsers", allUsers);

        if (stateKeeper.getCurrentUserDto() == null) {
            stateKeeper.setCurrentUserDto(new UserDto());
        }

        model.addAttribute("user", stateKeeper.getCurrentUserDto());
        model.addAttribute("selectedUser", stateKeeper.getCurrentUserDto().getUserCode());
        model.addAttribute("processStep", stateKeeper.getProcessStep());
        model.addAttribute("isLoggedInUser", userService.loggedInUser(stateKeeper.getCurrentUserDto()));
        
        return "userAccountPage";
    }

    @PreAuthorize("hasAnyAuthority({'BARTENDER', 'BARMANAGER'})")
    @GetMapping("/accounts/addNew")
    protected String addNewUser(
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        stateKeeper.processAdduser();

        return "redirect:/accounts";
    }

    @PreAuthorize("hasAuthority('BARTENDER')")
    @PostMapping("/accounts/addNew/BT")
    protected String saveNewUserBartender(
            @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        if (!result.hasErrors()) {
            doSave(userDto, stateKeeper);
        }

        return "redirect:/accounts";
    }

    @PreAuthorize("hasAuthority('BARMANAGER')")
    @PostMapping("/accounts/addNew/BM")
    protected String saveNewUserBarmanager(
            @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        if (!result.hasErrors()) {
            doSave(userDto, stateKeeper);
        }

        return "redirect:/accounts";
    }

    private void doSave(UserDto userDto,
                        @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        stateKeeper.setCurrentUserDto(userDto);

        if (userDto.getFullName() == null || userDto.getFullName() == "") {
            throw new InputMismatchException("The full name must be filled in");
        }

        userService.newUser(stateKeeper.getCurrentUserDto());

        clearForm(stateKeeper);
    }


    @GetMapping("/accounts/{userCode}")
    protected String selectUser(
            @PathVariable("userCode") String userCode,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper) {

        accountPageStateKeeper.setCurrentUserDto(userService.findDtoByUserCode(Integer.parseInt(userCode)));
        accountPageStateKeeper.processUserSelected();

        return "redirect:/accounts";
    }

    @PreAuthorize("hasAuthority('BARMANAGER')")
    @GetMapping("/accounts/editUser")
    protected String editUser(@SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {

        stateKeeper.processEditUser();

        return "redirect:/accounts";
    }

    @PreAuthorize("hasAuthority('BARMANAGER')")
    @PostMapping("/accounts/edit")
    protected String saveExistingUser(
            @ModelAttribute("user") UserDto userDto, BindingResult result,
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper) {

        if (!result.hasErrors()) {
            accountPageStateKeeper.setCurrentUserDto(userDto);
            userService.editUser(accountPageStateKeeper.getCurrentUserDto());

            clearForm(accountPageStateKeeper);
        }
        return "redirect:/accounts";
    }

    @PreAuthorize("hasAuthority('BARMANAGER')")
    @GetMapping("accounts/delete")
    protected String deleteUser(
            @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper accountPageStateKeeper) {

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

    @PreAuthorize("hasAnyAuthority({'BARTENDER', 'BARMANAGER'})")
    @GetMapping("/accounts/addCredit")
    protected String showAddCreditForm(@SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper,
                                       Model model) {
        stateKeeper.processAddCredit();
        model.addAttribute("prepaidBalance", stateKeeper.getCurrentUserDto().getPrepaidBalance());

        return "redirect:/accounts";
    }

    @PreAuthorize("hasAnyAuthority({'BARTENDER', 'BARMANAGER'})")
    @PostMapping("/accounts/addCredit")
    protected String addCredit(@ModelAttribute("user") UserDto userDto, // needed to make the method work
                               @RequestParam("credit") Float credit,
                               BindingResult result,
                               @SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {
        if (!result.hasErrors()) {
            BigDecimal amount = BigDecimal.valueOf(credit).setScale(2, RoundingMode.HALF_EVEN);
            userService.addCredit(stateKeeper.getCurrentUserDto().getUserCode(), amount);

            stateKeeper.setCurrentUserDto(userService.findDtoByUserCode(stateKeeper.getCurrentUserDto().getUserCode()));

            stateKeeper.processUserSelected();
        }
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/close")
    protected String closeEditUser(@SessionAttribute("accountPageStateKeeper") AccountPageStateKeeper stateKeeper) {
        stateKeeper.processUserSelected();

        return "redirect:/accounts";
    }
}
