package com.io.traderbook.repository;

import com.io.traderbook.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by kklimek on 31/03/14.
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "select u from User u")
    List<User> selectAllUsers();

}
