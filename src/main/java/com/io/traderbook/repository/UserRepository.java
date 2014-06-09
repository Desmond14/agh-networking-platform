package com.io.traderbook.repository;

import com.io.traderbook.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select u from User u")
    List<User> selectAllUsers();

    @Query(value = "select u from User u where u.username = ?1")
    User findByName(String name);

    @Query(value = "select u from User u where u.id = ?1")
    User findById(Integer id);

    @Modifying
    @Transactional(readOnly = false)
    @Query("UPDATE User u SET u.friends = ?1 WHERE u.username = ?2")
    Integer setNewFriendsForId(Set<User> friends, String username);
}
