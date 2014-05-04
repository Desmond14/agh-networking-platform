package com.io.traderbook.controller;

import com.io.traderbook.model.Offer;
import com.io.traderbook.model.OfferDiscussionPost;
import com.io.traderbook.model.User;
import com.io.traderbook.service.OfferDiscussionPostService;
import com.io.traderbook.service.OfferService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy
 * Date: 3/30/14
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
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

    @RequestMapping(value = "/offers/{offerId}", method = RequestMethod.GET)
    public ModelAndView renderOfferDiscussion(@PathVariable("offerId") Long offerId) throws Exception {
        ModelAndView modelAndView  = new ModelAndView("offerDiscussion");
        Offer offer = offerService.findById(offerId);
        if (offer == null) {
            modelAndView.addObject("notFound", true);
            return modelAndView;
        }
        modelAndView.addObject("offer", offer);
        List<OfferDiscussionPost> discussionPosts = offer.getDiscussionPosts();
        modelAndView.addObject("posts", discussionPosts);
        modelAndView.addObject("username", principal.getName());
        return modelAndView;
    }

    @RequestMapping(value = "/offers/{offerId}",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public String addPost(@PathVariable("offerId") Long offerId, @RequestBody OfferDiscussionPost newPost) {
        Offer offer = offerService.findById(offerId);
        if (offer == null) {
            return "offers";
        }
        newPost.setCorrespondingOffer(offer);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getUserByName(username);
        if (user == null) {
            return "offers";
        }
        newPost.setPostAuthor(user);
        offerDiscussionPostService.save(newPost);
        return "offers/" + offerId;
    }

    @RequestMapping(value = "/addOffer", method = RequestMethod.GET)
    public String addOfferForm(ModelMap modelMap, Principal principal){
        modelMap.addAttribute("offer", new Offer());
        modelMap.addAttribute("username", principal.getName());
        return "addOffer";
    }

    @RequestMapping(value = "/addOffer",
            method = RequestMethod.POST)
    public String addOffer(Offer offer, BindingResult result, ModelMap modelMap, Principal principal) {
        if(result.hasErrors()) {
            return "addOffer";
        }
        offer.setSeller(userService.getByName(principal.getName()));
        offerService.save(offer);
        return "redirect:/offers";
    }
}
