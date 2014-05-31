package com.io.traderbook.service;

import com.io.traderbook.model.Group;
import com.io.traderbook.model.User;
import com.io.traderbook.repository.GroupRepository;
import com.io.traderbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(Group group, String name) {
        User user = userRepository.findByName(name);
        user.getGroups().add(group);
        userRepository.save(user);
    }

}
