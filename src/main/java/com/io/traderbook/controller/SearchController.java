package com.io.traderbook.controller;

import com.io.traderbook.model.Offer;
import com.io.traderbook.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private OfferService offerService;

    @RequestMapping(value = "/offers/search")
    public ModelAndView renderSearchOfferForm(Principal principal, HttpServletRequest request) {
        ModelAndView searchForm = new ModelAndView("searchOffers");
        searchForm.addObject("username", principal.getName());
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String searchString = request.getParameter("searchString");
            searchForm.addObject("searchString", searchString);
            if (searchString == null || searchString.trim().length() == 0)
                return searchForm;

            Iterable<Offer> offers = offerService.getAll();
            List<Offer> foundedOffers = new ArrayList<Offer>();

            for (Offer offer : offers) {
                if (offer.getTitle().toLowerCase().contains(searchString.toLowerCase()) || offer.getContent().toLowerCase().contains(searchString.toLowerCase())) {
                    foundedOffers.add(offer);
                }
            }
            searchForm.addObject("foundedOffers", foundedOffers);
        }
        return searchForm;
    }

    @RequestMapping(value = "/offers/searchByLocation")
    public ModelAndView renderSearchOfferByLocationForm(Principal principal, HttpServletRequest request) {
        ModelAndView searchForm = new ModelAndView("searchOffersByLocation");
        searchForm.addObject("username", principal.getName());
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String locationString = request.getParameter("locationString");
            searchForm.addObject("locationString", locationString);
            if (locationString == null || locationString.trim().length() == 0)
                return searchForm;

            Iterable<Offer> offers = offerService.getAll();
            searchForm.addObject("offers", offers);
        }
        return searchForm;
    }
}
