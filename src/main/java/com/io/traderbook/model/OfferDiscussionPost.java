package com.io.traderbook.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy
 * Date: 3/29/14
 * Time: 11:16 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "OfferDiscussionPosts")
public class OfferDiscussionPost {

    @Id
    @GeneratedValue
    private long id;

    @Column(length = 255)
    private String postContent;
    //@ManyToOne
    //private User postAuthor;

    @ManyToOne
    @JoinColumn(name="OFFER_ID")
    private Offer correspondingOffer;

    protected OfferDiscussionPost() {

    }

    public OfferDiscussionPost(User postAuthor, String postContent) {
        //this.postAuthor = postAuthor;
        this.postContent = postContent;
    }

//    private User getPostAuthor() {
//        return postAuthor;
//    }
//
//    private void setPostAuthor(User postAuthor) {
//        this.postAuthor = postAuthor;
//    }

    public String getPostContent() {
        return postContent;
    }

    private void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    private long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    private Offer getCorrespondingOffer() {
        return correspondingOffer;
    }

    private void setCorrespondingOffer(Offer correspondingOffer) {
        this.correspondingOffer = correspondingOffer;
    }
}
