package com.io.traderbook.controller;

import com.io.traderbook.model.OfferDiscussionPost;
import com.io.traderbook.model.User;
import com.io.traderbook.service.OfferDiscussionPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Iterator;
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



    @RequestMapping(value = "/offers/{offerId}", method = RequestMethod.GET)
    public ModelAndView renderOfferDiscussion(@PathVariable("offerId") String offerId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("offerDiscussion");
        Iterator<OfferDiscussionPost> postsIterator = offerDiscussionPostService.findAll().iterator();
        List<OfferDiscussionPost> discussionPosts = new ArrayList<OfferDiscussionPost>();
        while(postsIterator.hasNext()) {
            discussionPosts.add(postsIterator.next());
        }
        if(discussionPosts.isEmpty()) {
            throw new Exception("List is empty!");
        }
        modelAndView.addObject("posts", discussionPosts);
        return modelAndView;
    }

    @RequestMapping(value = "/offers/{offerId}",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public String addPost(@PathVariable("offerId") String offerId, @RequestBody OfferDiscussionPost post) {
        offerDiscussionPostService.save(new OfferDiscussionPost(new User("user"), post.getPostContent()));
        return "offers/" + offerId;
    }
}
