package com.io.traderbook.service;

import com.io.traderbook.model.Group;
import com.io.traderbook.model.User;
import com.io.traderbook.repository.GroupRepository;
import com.io.traderbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    public Collection<Group> getAll() {
        return (Collection<Group>) groupRepository.findAll();
    }

    public Set<Group> findUserGroups(String name) {
        User user = userRepository.findByName(name);
        return user.getGroups();
    }

}
