package com.io.traderbook.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

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

    @NotEmpty(message = "Offer description cannot be empty")
    private String content;

    @NotEmpty(message = "Offer title cannot be empty")
    private String title;

    private String location;
    
    private int price;
    
    
    public String getLocation(){
    	return location;
    }
    
    public void setLocation(String location){
    	this.location = location;
    }

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
