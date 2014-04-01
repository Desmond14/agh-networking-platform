package com.io.traderbook.repository;

import com.io.traderbook.model.OfferDiscussionPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy
 * Date: 3/30/14
 * Time: 12:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface OfferDiscussionPostRepository extends CrudRepository<OfferDiscussionPost, Long> {

    @Query(value = "select o from OfferDiscussionPost o")
    List<OfferDiscussionPost> selectAllPosts();
}
