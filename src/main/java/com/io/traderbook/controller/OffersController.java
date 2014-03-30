package com.io.traderbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy
 * Date: 3/30/14
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class OffersController {

    @RequestMapping(value = "/offers/{offerId}", method = RequestMethod.GET)
    public ModelAndView renderOfferDiscussion(@PathVariable("offerId") String offerId) {
        ModelAndView modelAndView = new ModelAndView("offerDiscussion");
        return modelAndView;
    }
}
