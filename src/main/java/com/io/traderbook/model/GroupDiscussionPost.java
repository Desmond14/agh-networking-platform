package com.io.traderbook.model;

import javax.persistence.*;

@Entity
@Table(name = "GroupDiscussionPosts")
public class GroupDiscussionPost {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 255)
    private String postContent;
    @ManyToOne
    private User postAuthor;

    @ManyToOne
    @JoinColumn(name="GROUP_ID")
    private Group correspondingGroup;

    protected GroupDiscussionPost() {

    }

    public GroupDiscussionPost(User postAuthor, String postContent, Group correspondingGroup) {
        this.postAuthor = postAuthor;
        this.postContent = postContent;
        this.correspondingGroup = correspondingGroup;
    }

    public User getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(User postAuthor) {
        this.postAuthor = postAuthor;
    }

    public String getPostContent() {
        return postContent;
    }

    private void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    private long getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    private Group getCorrespondingGroup() {
        return correspondingGroup;
    }

    public void setCorrespondingGroup(Group correspondingGroup) {
        this.correspondingGroup = correspondingGroup;
    }
}
