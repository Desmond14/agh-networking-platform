package com.io.traderbook.model;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: slakomy
 * Date: 3/29/14
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private User() {

    }

    public User(String name) {
        this.name = name;
    }

    private int getId() {
        return id;
    }

    private String getName() {
        return name;
    }
}
