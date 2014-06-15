package com.io.traderbook.repository;

import com.io.traderbook.model.GroupDiscussionPost;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupDiscussionPostRepository extends CrudRepository<GroupDiscussionPost, Integer> {

    @Query(value = "select g from GroupDiscussionPost g")
    List<GroupDiscussionPost> selectAllPosts();

}
