package com.io.traderbook.service;

import com.io.traderbook.model.GroupDiscussionPost;
import com.io.traderbook.repository.GroupDiscussionPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupDiscussionPostService {

    @Autowired
    private GroupDiscussionPostRepository groupDiscussionPostRepository;

    public Iterable<GroupDiscussionPost> findAll() {
        return groupDiscussionPostRepository.findAll();
    }

    public void save(GroupDiscussionPost groupDiscussionPost) {
        groupDiscussionPostRepository.save(groupDiscussionPost);
    }

    public List<GroupDiscussionPost> selectAllPosts() {
        return groupDiscussionPostRepository.selectAllPosts();
    }

}
