package com.io.traderbook.service;

import com.io.traderbook.model.User;
import com.io.traderbook.model.UserRoles;
import com.io.traderbook.repository.UserRepository;
import com.io.traderbook.repository.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRolesRepository userRolesRepository;

    @PersistenceContext(unitName="uaiContactsPU") private EntityManager entityManager;

    public void insertNewUser(User user) {
        user.setEnabled(true);
        UserRoles userRoles = new UserRoles(user.getId(), Roles.ROLE_USER.toString(), user);
        userRepository.save(user);
        userRolesRepository.save(userRoles);
    }

    public Iterable<UserRoles> findAllUserRoles() {
        return userRolesRepository.findAll();
    }

    public void deleteUserRole(UserRoles userRoles) {
        userRolesRepository.delete(userRoles);
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


    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    public User getById(Integer id) {
        return userRepository.findById(id);
    }

    @Transactional
    public Integer setNewFriendsForId(Set<User> friends, Integer userId) {
        User user = entityManager.find(User.class, userId);
        user.setFriends(friends);
        entityManager.flush();
        entityManager.clear();
        return user.getId();
    }

    public void delete(Integer id) {
        userRepository.delete(id);
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
