package com.io.traderbook.controller;

import com.io.traderbook.model.Rating;
import com.io.traderbook.model.User;
import com.io.traderbook.service.RatingService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
	
	@Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;
    
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public ModelAndView renderRatings(@PathVariable("userId") Integer userId, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("user");
        org.springframework.security.core.userdetails.User userTmp = (org.springframework.security.core.userdetails.User) ((Authentication)principal).getPrincipal();
		String name = userTmp.getUsername();
        User viewedUser = userService.getById(userId);
        if (viewedUser == null) {
            modelAndView.addObject("notFound", true);
            return modelAndView;
        }
        List<Rating> ratingsReceived = ratingService.findByRateeId(viewedUser.getId());

        modelAndView.addObject("username", name);
        modelAndView.addObject("viewedUser", viewedUser);
        modelAndView.addObject("ratingsReceived", ratingsReceived);

        return modelAndView;
    }
    
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST)
    public ModelAndView addRating(@PathVariable("userId") Integer userId, Principal principal, @RequestParam String buttonType) {
        ModelAndView modelAndView = new ModelAndView("user");
        org.springframework.security.core.userdetails.User userTmp = (org.springframework.security.core.userdetails.User) ((Authentication)principal).getPrincipal();
		String name = userTmp.getUsername();
		User userRating = userService.getByName(name);
        User userRated = userService.getById(userId);
        if (userRated == null) {
            modelAndView.addObject("notFound", true);
            return modelAndView;
        }
        List<Rating> ratingsReceived = ratingService.findByRateeId(userRated.getId());
        if (!userRated.equals(userRating)) {
	        boolean found = false;
	        if(buttonType.equals("rateUp")){
		        for(Rating r : ratingsReceived){
		        	if(r.getRater().equals(userRating)){
		        		if(!r.isPositive()){
		        			r.setPositive(true);
		    	    		ratingService.save(r);
		        		}
		        		found = true;
		        	}
		        }
		    	if(!found){
		    		Rating newRating = new Rating();
		    		newRating.setRater(userRating);
		    		newRating.setRatee(userRated);
		    		newRating.setPositive(true);
		    		ratingService.save(newRating);
		    	}
	        } else if(buttonType.equals("rateDown")) {
		        for(Rating r : ratingsReceived){
		        	if(r.getRater().equals(userRating)){
		        		if(r.isPositive()){
		        			r.setPositive(false);
		    	    		ratingService.save(r);
		        		}
		        		found = true;
		        	}
		        }
		    	if(!found){
		    		Rating newRating = new Rating();
		    		newRating.setRater(userRating);
		    		newRating.setRatee(userRated);
		    		newRating.setPositive(false);
		    		ratingService.save(newRating);
		    	}
	        }
	        ratingsReceived = ratingService.findByRateeId(userRated.getId());
        }

        modelAndView.addObject("username", name);
        modelAndView.addObject("viewedUser", userRated);
        modelAndView.addObject("ratingsReceived", ratingsReceived);

        return modelAndView;
    }
	
}