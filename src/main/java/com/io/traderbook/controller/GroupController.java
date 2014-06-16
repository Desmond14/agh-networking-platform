package com.io.traderbook.controller;

import com.io.traderbook.model.Group;
import com.io.traderbook.model.GroupDiscussionPost;
import com.io.traderbook.model.GroupForm;
import com.io.traderbook.model.User;
import com.io.traderbook.service.GroupDiscussionPostService;
import com.io.traderbook.service.GroupService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupDiscussionPostService groupDiscussionPostService;

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

    @RequestMapping(value = "/groups", params = "leftGroup")
    public String afterLeftGroup(ModelMap modelMap) {
        modelMap.addAttribute("leftGroup", "true");
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
        modelAndView.addObject("groupForm", new GroupForm());

        return modelAndView;
    }

    @RequestMapping(value = "/groups/join", method = RequestMethod.POST)
    public ModelAndView joinGroup(@ModelAttribute @Valid GroupForm groupForm, BindingResult result, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("joinGroup");

        Set<Group> otherGroups = groupService.findOtherGroups(principal.getName());
        modelAndView.addObject("otherGroups", otherGroups);

        if (result.hasErrors()) {
            return modelAndView;
        }

        groupService.joinToGroup(principal.getName(), groupForm.getGroupName());

        return new ModelAndView("redirect:/groups?joinedGroup=" + groupForm.getGroupName());

    }

    @RequestMapping(value = "/groups/leave", method = RequestMethod.GET)
    public ModelAndView renderLeaveGroupForm(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("leaveGroup");

        Set<Group> userGroups = groupService.findUserGroups(principal.getName());
        modelAndView.addObject("userGroups", userGroups);
        modelAndView.addObject("groupForm", new GroupForm());

        return modelAndView;
    }

    @RequestMapping(value = "/groups/leave", method = RequestMethod.POST)
    public ModelAndView leaveGroup(@ModelAttribute @Valid GroupForm groupForm, BindingResult result, Principal principal) {
        ModelAndView modelAndView = new ModelAndView("leaveGroup");

        Set<Group> userGroups = groupService.findUserGroups(principal.getName());
        modelAndView.addObject("userGroups", userGroups);

        if (result.hasErrors()) {
            return modelAndView;
        }

        groupService.leaveGroup(principal.getName(), groupForm.getGroupName());

        return new ModelAndView("redirect:/groups?leftGroup=" + groupForm.getGroupName());

    }

    @RequestMapping(value = "/groups/mygroups/{groupId}", method = RequestMethod.GET)
    public ModelAndView renderOfferDiscussion(@PathVariable("groupId") Integer groupId) throws Exception {
        ModelAndView modelAndView = new ModelAndView("groupDiscussion");
        Group group = groupService.findById(groupId);
        if (group == null) {
            modelAndView.addObject("notFound", true);
            return modelAndView;
        }
        modelAndView.addObject("group", group);
        List<GroupDiscussionPost> discussionPosts = group.getDiscussionPosts();
        modelAndView.addObject("posts", discussionPosts);
        return modelAndView;
    }

    @RequestMapping(value = "/groups/mygroups/{groupId}",
            method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> addPost(@PathVariable("groupId") Integer groupId, @RequestBody GroupDiscussionPost newPost) {
        Group group = groupService.findById(groupId);
        if (group == null) {
            return new ResponseEntity<String>(HttpStatus.METHOD_FAILURE);
        }
        newPost.setCorrespondingGroup(group);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.getByName(username);
        if (user == null) {
            return new ResponseEntity<String>(HttpStatus.METHOD_FAILURE);
        }
        newPost.setPostAuthor(user);
        groupDiscussionPostService.save(newPost);
        return new ResponseEntity<String>(HttpStatus.OK);
    }

}
