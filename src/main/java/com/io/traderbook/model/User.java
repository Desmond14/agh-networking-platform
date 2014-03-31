package com.io.traderbook.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy, kklimek
 * Date: 3/29/14
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "users")
public class User {

    private Integer id;

    @Size(min = 3, max = 20, message = "Username must consist of 3 to 20 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username must consist of alphanumeric characters with no spaces.")
    private String username;
    @Size(min = 6, max = 20, message = "Password cannot be shorter than 6 and longer than 20 characters.")
    private String password;

    private boolean enabled;

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

    @Column(name = "ENABLED", nullable = false)
    public boolean getEnabled() {
        return enabled;
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

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
