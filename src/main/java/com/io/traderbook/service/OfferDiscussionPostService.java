package com.io.traderbook.service;

import com.io.traderbook.model.OfferDiscussionPost;
import com.io.traderbook.repository.OfferDiscussionPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy
 * Date: 3/30/14
 * Time: 12:15 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class OfferDiscussionPostService {

    @Autowired
    private OfferDiscussionPostRepository offerDiscussionPostRepository;

    public Iterable<OfferDiscussionPost> findAll() {
        return offerDiscussionPostRepository.findAll();
    }
}
