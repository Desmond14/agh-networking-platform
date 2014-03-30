package com.io.traderbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kklimek on 30/03/14.
 */
@Controller
public class RegisterController {

    @RequestMapping("/register")
    public String register() {
        return "register";
    }
}
