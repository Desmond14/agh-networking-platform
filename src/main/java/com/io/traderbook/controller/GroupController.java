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

import java.security.Principal;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

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
        groupService.save(group, principal.getName());
        return "redirect:/groups";
    }
}
