package com.io.traderbook.service;

import com.io.traderbook.model.Rating;
import com.io.traderbook.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    
    public void save(Rating rating) {
    	ratingRepository.save(rating);
    }

    public List<Rating> findByRaterId(Integer id) {
        return ratingRepository.findByRaterId(id);
    }

    public List<Rating> findByRateeId(Integer id) {
        return ratingRepository.findByRateeId(id);
    }

}