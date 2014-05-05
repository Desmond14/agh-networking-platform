package com.io.traderbook.controller;
 
import java.security.Principal;

import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String index(ModelMap model, Principal principal) {
        User user = (User) ((Authentication)principal).getPrincipal();
		String name = user.getUsername();
        String password = user.getPassword();
        com.io.traderbook.model.User user1 = userService.getByName(name);
        model.addAttribute("user", user1);
		model.addAttribute("username", name);
        model.addAttribute("password", password);
        model.addAttribute("email", user1.getEmail());
        model.addAttribute("country", user1.getCountry());
        model.addAttribute("city", user1.getCity());

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
	public String loginerror(ModelMap model) {
		model.addAttribute("error", "true");
		return "login";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
        model.addAttribute("logout", "true");
		return "login";
	}
	
}