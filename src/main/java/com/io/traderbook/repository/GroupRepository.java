package com.io.traderbook.repository;

import com.io.traderbook.model.Group;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Integer> {

    @Query(value = "select g from Group g where g.groupName = ?1")
    Group findByName(String groupName);

    @Query(value = "select g from Group g where g.id = ?1")
    Group findById(Integer groupId);
}
