package com.MIW.Cohort5.Clups.controller;

import com.MIW.Cohort5.Clups.dtos.UserDto;
import com.MIW.Cohort5.Clups.dtos.stateKeeper.ProductPageStateKeeper;
import com.MIW.Cohort5.Clups.services.implementations.ClupsUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author S.K.C.Reijntjes
 *
 * This controller arranges all views and actions for the Users page
 */

@Controller
@SessionAttributes("productPageStateKeeper")
public class UserPageController {

    public final ClupsUserDetailsServiceImpl userService;

    @Autowired
    public UserPageController(ClupsUserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("productPageStateKeeper")
    public ProductPageStateKeeper productPageStateKeeper() { return new ProductPageStateKeeper(); }

    @GetMapping({"/users"})
    protected String showPage(Model model,
                              @ModelAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {
        model.addAttribute("allUsers", userService.getAll());
        model.addAttribute("selectedPage", "userPage");
        model.addAttribute("formUserState", productPageStateKeeper.isShowUserForm());
        model.addAttribute("user", new UserDto());

        return "userEditor";
    }

    @GetMapping({"/users/addNew"})
    protected String addNewUser
            (@SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        if (!productPageStateKeeper.isShowUserForm()) {
            productPageStateKeeper.setUserForm(true);
        }

        return "redirect:/users";
    }

    @PostMapping({"/users/addNew"})
    protected String saveNewUser
            (@ModelAttribute("user") UserDto userDto, BindingResult result,
             @SessionAttribute("productPageStateKeeper") ProductPageStateKeeper productPageStateKeeper) {

        if (!result.hasErrors()) {
            userService.addUser(userDto);
        }

        productPageStateKeeper.setUserForm(false);

        return "redirect:/users";
    }

}
