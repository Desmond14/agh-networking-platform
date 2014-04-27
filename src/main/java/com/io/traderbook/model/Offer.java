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

    @OneToMany(mappedBy = "correspondingOffer", fetch = FetchType.EAGER)
    private List<OfferDiscussionPost> discussionPosts;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private User seller;

    private String content;

    private String title;
    private int price;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Offer() {

    }

    public Offer(User seller) {
        this.seller = seller;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<OfferDiscussionPost> getDiscussionPosts() {
        return discussionPosts;
    }

    private void setDiscussionPosts(List<OfferDiscussionPost> discussionPosts) {
        this.discussionPosts = discussionPosts;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    @Override
    public String toString(){
        return "Offer no. " + getId();
    }

}
