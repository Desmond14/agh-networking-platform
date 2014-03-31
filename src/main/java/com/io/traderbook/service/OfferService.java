package com.io.traderbook.service;

import com.io.traderbook.model.Offer;
import com.io.traderbook.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy
 * Date: 3/31/14
 * Time: 11:32 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public Offer findById(Long id) {
        return offerRepository.findById(id);
    }

}
