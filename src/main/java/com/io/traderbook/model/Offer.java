package com.io.traderbook.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy
 * Date: 3/31/14
 * Time: 10:52 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "Offers")
public class Offer {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany(mappedBy = "correspondingOffer")
    private List<OfferDiscussionPost> discussionPosts;

    @OneToOne
    private User seller;

    private Offer() {

    }

    public Offer(User seller) {
        this.seller = seller;
    }


    private long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public List<OfferDiscussionPost> getDiscussionPosts() {
        return discussionPosts;
    }

    private void setDiscussionPosts(List<OfferDiscussionPost> discussionPosts) {
        this.discussionPosts = discussionPosts;
    }

    private User getSeller() {
        return seller;
    }

    private void setSeller(User seller) {
        this.seller = seller;
    }

}
