package com.io.traderbook.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @Column(name = "GROUP_ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "GROUP_NAME", length = 20)
    private String groupName;

    @Column(length = 255)
    private String description;

    @ManyToMany(mappedBy = "groups")
    private Set<User> users = new HashSet<User>();

    public Group(String groupName, String description) {
        this.groupName = groupName;
        this.description = description;
    }

    public Group() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
