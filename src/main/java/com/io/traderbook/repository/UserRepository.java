package com.io.traderbook.repository;

import com.io.traderbook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select u from User u")
    List<User> selectAllUsers();

    @Query(value = "select u from User u where u.username = ?1")
    User findByName(String name);

}
