package com.io.traderbook.model;

import javax.persistence.*;

@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private User sender;

    @OneToOne
    private User receiver;

    private String topic;

    private String content;

    @OneToOne
    private Offer correspondingOffer;

    public Message() {

    }

    public Message(User sender, User receiver, String content, String topic) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.topic = topic;
    }

    private long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Offer getCorrespondingOffer() {
        return correspondingOffer;
    }

    public void setCorrespondingOffer(Offer correspondingOffer) {
        this.correspondingOffer = correspondingOffer;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
