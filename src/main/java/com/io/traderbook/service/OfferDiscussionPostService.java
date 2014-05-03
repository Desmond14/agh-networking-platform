package com.io.traderbook.service;

import com.io.traderbook.model.OfferDiscussionPost;
import com.io.traderbook.repository.OfferDiscussionPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferDiscussionPostService {

    @Autowired
    private OfferDiscussionPostRepository offerDiscussionPostRepository;

    public Iterable<OfferDiscussionPost> findAll() {
        return offerDiscussionPostRepository.findAll();
    }

    public void save(OfferDiscussionPost post) {
        offerDiscussionPostRepository.save(post);
    }

    public List<OfferDiscussionPost> selectAllPosts() {
        return offerDiscussionPostRepository.selectAllPosts();
    }
}
