package com.io.traderbook.controller;
 
import java.security.Principal;

import com.io.traderbook.model.Rating;
import com.io.traderbook.service.UserService;
import com.io.traderbook.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import java.util.List;

@Controller
public class LoginController {
	
	@Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;

	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String index(ModelMap model, Principal principal) {
        User user = (User) ((Authentication)principal).getPrincipal();
		String name = user.getUsername();
        String password = user.getPassword();
        com.io.traderbook.model.User user1 = userService.getByName(name);
        List<Rating> ratingsReceived = ratingService.findByRateeId(user1.getId());
        List<Rating> ratingsGiven = ratingService.findByRaterId(user1.getId());
        model.addAttribute("user", user1);
		model.addAttribute("username", name);
        model.addAttribute("password", password);
        model.addAttribute("email", user1.getEmail());
        model.addAttribute("country", user1.getCountry());
        model.addAttribute("city", user1.getCity());
        model.addAttribute("ratingsReceived", ratingsReceived);
        model.addAttribute("ratingsGiven", ratingsGiven);

		return "loggedIn";

	}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexRedirect(Principal principal) {
        if(principal == null)
            return "redirect:/login";
        else
            return "redirect:/welcome";
    }

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
		return "login";
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginError(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
        model.addAttribute("logout", "true");
		return "login";
	}
	
}