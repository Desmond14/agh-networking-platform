package com.io.traderbook.service;

import com.io.traderbook.model.Group;
import com.io.traderbook.model.User;
import com.io.traderbook.repository.GroupRepository;
import com.io.traderbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class GroupService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Transactional
    public void save(Group group, String name) {
        User user = userRepository.findByName(name);
        user.getGroups().add(group);
        userRepository.save(user);
    }

    public Iterable<Group> getAll() {
        return groupRepository.findAll();
    }

    public Set<Group> findUserGroups(String name) {
        User user = userRepository.findByName(name);
        return user.getGroups();
    }

    public Set<Group> findOtherGroups(String name) {
        Iterable<Group> groups = getAll();
        User user = userRepository.findByName(name);
        Set<Group> usersGroups = user.getGroups();
        Set<Group> otherGroups = new HashSet<Group>();

        for (Group g : groups) {
            boolean check = true;
            for (Group ug : usersGroups) {
                if (ug.getId().equals(g.getId())) {
                    check = false; break;
                }
            }
            if (check)
                otherGroups.add(g);
        }
        return otherGroups;
    }

}
