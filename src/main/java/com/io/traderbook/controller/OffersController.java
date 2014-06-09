package com.io.traderbook.controller;

import com.io.traderbook.model.Offer;
import com.io.traderbook.model.OfferDiscussionPost;
import com.io.traderbook.model.User;
import com.io.traderbook.service.OfferDiscussionPostService;
import com.io.traderbook.service.OfferService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
public class OffersController {

    @Autowired
    private OfferDiscussionPostService offerDiscussionPostService;
    @Autowired
    private OfferService offerService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/offers")
    public ModelAndView renderOfferList(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("offerList");
        Iterable<Offer> offers = offerService.getAll();
        modelAndView.addObject("offers", offers);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }
    
    @RequestMapping(value = "/offers", method = RequestMethod.GET)
    public ModelAndView renderFilteredOfferList(Principal principal, HttpServletRequest request) {
        ModelAndView filteredOffers = new ModelAndView("offerList");
        filteredOffers.addObject("username", principal.getName());
        String tmpVal = request.getParameter("minPrice");
        if(tmpVal != null && tmpVal.trim().length() > 0){
        	Integer minPrice = Integer.valueOf(tmpVal);
    		filteredOffers.addObject("minPrice", minPrice);
        }
        tmpVal = request.getParameter("maxPrice");
        if(tmpVal != null && tmpVal.trim().length() > 0){
        	Integer maxPrice = Integer.valueOf(tmpVal);
        	filteredOffers.addObject("maxPrice", maxPrice);
        }

        Iterable<Offer> offers = offerService.getAll();
        filteredOffers.addObject("offers", offers);
        return filteredOffers;
    }

    @RequestMapping(value = "/offers/{offerId}", method = RequestMethod.GET)
    public ModelAndView renderOfferDiscussion(@PathVariable("offerId") Long offerId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("offerDiscussion");
        Offer offer = offerService.findById(offerId);
        if (offer == null) {
            modelAndView.addObject("notFound", true);
            return modelAndView;
        }
        modelAndView.addObject("offer", offer);
        List<OfferDiscussionPost> discussionPosts = offer.getDiscussionPosts();
        modelAndView.addObject("posts", discussionPosts);
        return modelAndView;
    }

    @RequestMapping(value = "/offers/{offerId}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> addPost(@PathVariable("offerId") Long offerId, @RequestBody OfferDiscussionPost newPost) {
        Offer offer = offerService.findById(offerId);
        if (offer == null) {
            return new ResponseEntity<String>(HttpStatus.METHOD_FAILURE);
        }
        newPost.setCorrespondingOffer(offer);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getByName(username);
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.METHOD_FAILURE);
        }
        newPost.setPostAuthor(user);
        offerDiscussionPostService.save(newPost);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    @RequestMapping(value = "/addOffer", method = RequestMethod.GET)
    public String addOfferForm(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("offer", new Offer());
        modelMap.addAttribute("username", principal.getName());
        return "addOffer";
    }

    @RequestMapping(value = "/addOffer",
            method = RequestMethod.POST)
    public String addOffer(@Valid Offer offer, BindingResult result, ModelMap modelMap, Principal principal) {
        if(result.hasErrors()) {
            return "addOffer";
        }
        offer.setSeller(userService.getByName(principal.getName()));
        offerService.save(offer);
        return "redirect:/offers";
    }
}
