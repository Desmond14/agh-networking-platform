package com.io.traderbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserFriendsController {

    @RequestMapping(value = "/friends")
    public ModelAndView displayUserFriendsView() {
        ModelAndView modelAndView = new ModelAndView("friends");
        return modelAndView;
    }

    @RequestMapping(value = "friends/find")
    public ModelAndView findFriendView() {
        ModelAndView modelAndView = new ModelAndView("friends");
        return modelAndView;
    }

}
