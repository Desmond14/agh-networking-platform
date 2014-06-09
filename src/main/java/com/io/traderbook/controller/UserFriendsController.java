package com.io.traderbook.controller;

import com.io.traderbook.model.Offer;
import com.io.traderbook.model.User;
import com.io.traderbook.service.OfferService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class UserFriendsController {

    @Autowired
    private UserService userService;

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "/friends")
    public ModelAndView displayUserFriendsView(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("friends");
        User user = userService.getByName(principal.getName());
        modelAndView.addObject("friends", user.getFriends());
        return modelAndView;
    }

    @RequestMapping(value = "friends/find")
    public ModelAndView findFriendView(Principal principal, HttpServletRequest request) {
        ModelAndView searchForm = new ModelAndView("searchUser");
        searchForm.addObject("username", principal.getName());
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String searchString = request.getParameter("searchString");
            searchForm.addObject("searchString", searchString);
            if (searchString == null || searchString.trim().length() == 0)
                return searchForm;

            User searchingUser = userService.getByName(principal.getName());
            Set<User> friends = searchingUser.getFriends();

            Iterable<User> users = userService.selectAllUsers();
            List<User> foundedUsers = new ArrayList<User>();

            for (User user : users) {
                if (user.getUsername().toLowerCase().contains(searchString.toLowerCase())) {
                    if (!friends.contains(user) || searchingUser.equals(user)) {
                        foundedUsers.add(user);
                    }
                }
            }
            searchForm.addObject("foundedUsers", foundedUsers);
        }
        return searchForm;
    }

    @RequestMapping(value = "friends/add/{userId}")
    public String addFriend(@PathVariable("userId") Integer userId, Principal principal) {
        User invitingUser = userService.getByName(principal.getName());
        User invitedUser = userService.getById(userId);
        if (invitingUser == null || invitedUser == null) {
            return "/home";
        }
        invitingUser.getFriends().add(invitedUser);
        userService.setNewFriendsForId(invitingUser.getFriends(), invitingUser.getUsername());
        return "redirect:/friends";
    }

    @RequestMapping(value = "/friends/{friendId}")
    public ModelAndView showFriendOffers(@PathVariable("friendId") Integer friendId, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("offerList");
        User friend = userService.getById(friendId);
        Iterable<Offer> offers = offerService.findBySeller(friend);
        modelAndView.addObject("offers", offers);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }

}
