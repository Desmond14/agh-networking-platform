package com.io.traderbook.model;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRoles {

    private Integer id;
    private String authority;
    private User user;

    public UserRoles() {
    }

    public UserRoles(Integer id, String authority, User user) {
        this.id = id;
        this.authority = authority;
        this.user = user;
    }

    @Id
    @GeneratedValue
    @Column(name = "USER_ROLE_ID", nullable = false)
    public Integer getId() {
        return id;
    }

    @Column(name = "AUTHORITY", nullable = false)
    public String getAuthority() {
        return authority;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
