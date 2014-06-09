package com.io.traderbook.service;

import com.io.traderbook.model.Offer;
import com.io.traderbook.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.io.traderbook.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public Offer findById(Long id) {
        return offerRepository.findOne(id);
    }

    public Iterable<Offer> getAll() {
        return offerRepository.findAll();
    }

    public void save(Offer offer) {
        offerRepository.save(offer);
    }

    public Iterable <Offer> findBySeller(User seller) {
        return offerRepository.findBySeller(seller);
    }

}
