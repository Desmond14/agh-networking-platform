package com.io.traderbook.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    private Integer id;

    @Size(min = 3, max = 20, message = "Username must consist of 3 to 20 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must consist of alphanumeric characters with no spaces.")
    private String username;
    @Size(min = 4, max = 20, message = "Password cannot be shorter than 4 and longer than 20 characters.")
    private String password;
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "Invalid email address.")
    private String email;
    private String country;
    private String city;

    private boolean enabled;

    private Set<Group> groups = new HashSet<Group>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> friends;

    @ManyToMany(mappedBy = "users")
    private Set<User> friendOf;

    @Id
    @GeneratedValue
    @Column(name = "USER_ID", nullable = false)
    public Integer getId() {
        return id;
    }

    @Column(name = "USERNAME", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    @Column(name = "PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    @Column(name = "EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "COUNTRY", nullable = true)
    public String getCountry() {
        return country;
    }

    @Column(name = "CITY", nullable = true)
    public String getCity() {
        return city;
    }

    @Column(name = "ENABLED", nullable = false)
    public boolean getEnabled() {
        return enabled;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "users_groups",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GROUP_ID")})
    public Set<Group> getGroups() {
        return groups;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_FRIENDS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "FRIEND_ID"))
    public Set<User> getFriends() {
        return friends;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    @Override
    public boolean equals(Object object) {
        if(object == null){
            return false;
        }
        if(object.getClass() != User.class){
            return false;
        }
        User user = (User) object;
        return getUsername().equals(user.getUsername());
    }
}
