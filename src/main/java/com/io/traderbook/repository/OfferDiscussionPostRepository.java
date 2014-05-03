package com.io.traderbook.repository;

import com.io.traderbook.model.OfferDiscussionPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OfferDiscussionPostRepository extends CrudRepository<OfferDiscussionPost, Long> {

    @Query(value = "select o from OfferDiscussionPost o")
    List<OfferDiscussionPost> selectAllPosts();
}
