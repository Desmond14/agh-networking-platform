package com.io.traderbook.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class GroupForm {

    @NotNull @NotEmpty
    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
