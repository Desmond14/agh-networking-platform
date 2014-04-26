package com.io.traderbook.controller;

import com.io.traderbook.model.User;
import com.io.traderbook.model.UserRoles;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by kklimek on 30/03/14.
 */
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegisterForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUserFromRegisterForm(@Valid User user, BindingResult result, ModelMap modelMap) {
        if (result.hasErrors()) {
            return "register";
        } else if (userService.checkIfUserIsInDatabase(user.getUsername())) {
            modelMap.addAttribute("addUserError", "true");
            return "register";
        }
        //adding new user
        userService.insertNewUser(user);
        return "redirect:/";
    }

}
