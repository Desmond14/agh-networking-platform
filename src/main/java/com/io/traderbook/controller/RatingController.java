package com.io.traderbook.controller;

import com.io.traderbook.model.Rating;
import com.io.traderbook.model.User;
import com.io.traderbook.service.RatingService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class RatingController {
	
	@Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/welcome#profile", method = RequestMethod.GET)
    public ModelAndView renderRatings(Principal principal) {
        String username = principal.getName();
        User user = userService.getByName(username);
        List<Rating> ratingsReceived = ratingService.findByRateeId(user.getId());
        List<Rating> ratingsGiven = ratingService.findByRaterId(user.getId());

        ModelAndView modelAndView = new ModelAndView("welcome#profile");
        modelAndView.addObject("ratingsReceived", ratingsReceived);
        modelAndView.addObject("ratingsGiven", ratingsGiven);

        return modelAndView;
    }
	
}