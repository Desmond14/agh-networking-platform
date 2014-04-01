package com.io.traderbook.controller;

import com.io.traderbook.model.Offer;
import com.io.traderbook.model.OfferDiscussionPost;
import com.io.traderbook.service.OfferDiscussionPostService;
import com.io.traderbook.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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


    @RequestMapping(value = "/offers/{offerId}", method = RequestMethod.GET)
    public ModelAndView renderOfferDiscussion(@PathVariable("offerId") Long offerId) throws Exception {
        ModelAndView modelAndView  = new ModelAndView("offerDiscussion");
        Offer offer = offerService.findById(offerId);
        if (offer == null) {
            modelAndView.addObject("notFound", true);
            return modelAndView;
        }
        List<OfferDiscussionPost> discussionPosts = offer.getDiscussionPosts();
        modelAndView.addObject("posts", discussionPosts);
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
        offerDiscussionPostService.save(newPost);
        return "offers/" + offerId;
    }
}
