package com.io.traderbook.service;

import com.io.traderbook.model.User;
import com.io.traderbook.model.UserRoles;
import com.io.traderbook.repository.UserRepository;
import com.io.traderbook.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kklimek on 31/03/14.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    public void insertNewUser(User user) {
        user.setEnabled(true);
        UserRoles userRoles = new UserRoles(user.getId(), Roles.ROLE_USER.toString(), user);
        userRepository.save(user);
        userRolesRepository.save(userRoles);
    }

    public boolean checkIfUserIsInDatabase(String username) {
        List<User> users = selectAllUsers();
        for(User user : users) {
            if (user.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public List<User> selectAllUsers() {
        return userRepository.selectAllUsers();
    }
}

enum Roles {
    ROLE_USER("ROLE_USER"), ROLE_ADMIN("ROLE_ADMIN");

    String role;

    Roles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role;
    }
}
