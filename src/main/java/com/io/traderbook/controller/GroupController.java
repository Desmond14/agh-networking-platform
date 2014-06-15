package com.io.traderbook.controller;

import com.io.traderbook.model.Group;
import com.io.traderbook.model.JoinGroupForm;
import com.io.traderbook.model.User;
import com.io.traderbook.service.GroupService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    public String addGroup(@Valid Group group, BindingResult result, Principal principal) {
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

    @RequestMapping(value = "/groups", params = "joinedGroup")
    public String afterJoinedToGroup(ModelMap modelMap) {
        modelMap.addAttribute("joinedGroup", "true");
        return "groups";
    }

    @RequestMapping(value = "/groups/mygroups")
    public ModelAndView displayUserGroups(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("groupList");

        Set<Group> usersGroups = groupService.findUserGroups(principal.getName());
        modelAndView.addObject("usersGroups", usersGroups);
        Map<String, Integer> numOfMembersInGroup = new HashMap<String, Integer>();
        for (Group group : usersGroups) {
            numOfMembersInGroup.put(group.getGroupName(), group.getUsers().size());
        }
        modelAndView.addObject("numberOfMembersInGroup", numOfMembersInGroup);
        return modelAndView;
    }

    @RequestMapping(value = "/groups/join", method = RequestMethod.GET)
    public ModelAndView renderJoinGroupForm(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("joinGroup");

        Set<Group> otherGroups = groupService.findOtherGroups(principal.getName());
        modelAndView.addObject("otherGroups", otherGroups);
        modelAndView.addObject("joinGroupForm", new JoinGroupForm());

        return modelAndView;
    }

    @RequestMapping(value = "/groups/join", method = RequestMethod.POST)
    public ModelAndView joinGroup(@ModelAttribute @Valid JoinGroupForm joinGroupForm, BindingResult result, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("joinGroup");

        Set<Group> otherGroups = groupService.findOtherGroups(principal.getName());
        modelAndView.addObject("otherGroups", otherGroups);

        if (result.hasErrors()) {
            return modelAndView;
        }

        groupService.joinToGroup(principal.getName(), joinGroupForm.getGroupName());

        return new ModelAndView("redirect:/groups?joinedGroup=" + joinGroupForm.getGroupName());

    }

}
