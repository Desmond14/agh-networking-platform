package com.io.traderbook.controller;

import com.io.traderbook.model.Group;
import com.io.traderbook.model.User;
import com.io.traderbook.service.GroupService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.security.Principal;
import java.util.*;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/groups")
    public String displayGroupPanel() {
        return "groups";
    }

    @RequestMapping(value = "/groups/create", method = RequestMethod.GET)
    public String displayNewGroupForm(ModelMap modelMap) {
        modelMap.addAttribute("group", new Group());
        return "addGroup";
    }

    @Transactional
    @RequestMapping(value = "/groups/create", method = RequestMethod.POST)
    public String addGroup(Group group, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "addGroup";
        }
        groupService.createNewGroup(group, principal.getName());
        return "redirect:/groups?addedGroup=" + group.getGroupName();
    }

    @RequestMapping(value = "/groups", params = "addedGroup")
    public String afterAddedNewGroup(ModelMap modelMap) {
        modelMap.addAttribute("addedGroup", "true");
        return "groups";
    }

    @RequestMapping(value = "/groups/allgroups")
    public ModelAndView displayAllGroups(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("groupList");

        Set<Group> usersGroups = groupService.findUserGroups(principal.getName());
        modelAndView.addObject("usersGroups", usersGroups);

        Set<Group> otherGroups = groupService.findOtherGroups(principal.getName());
        modelAndView.addObject("otherGroups", otherGroups);
        return modelAndView;
    }

}
