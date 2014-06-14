package com.io.traderbook.controller;

import com.io.traderbook.model.Message;
import com.io.traderbook.model.MessageForm;
import com.io.traderbook.model.User;
import com.io.traderbook.service.MessageService;
import com.io.traderbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public ModelAndView renderMessageList(Principal principal) {
        String username = principal.getName();
        User user = userService.getByName(username);
        List<Message> messagesReceived = messageService.findByReceiverId(user.getId());
        List<Message> messagesSent = messageService.findBySenderId(user.getId());

        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject("messagesReceived", messagesReceived);
        modelAndView.addObject("messagesSent", messagesSent);

        return modelAndView;
    }


    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public ModelAndView renderMessageForm(Principal principal) {
        ModelAndView modelAndView = new ModelAndView("sendMessage");
        User user = userService.getByName(principal.getName());
        addOtherUsernamesToModel(modelAndView, user);
        modelAndView.addObject("messageForm", new MessageForm());
        return modelAndView;
    }

    @RequestMapping(value = "/sendMessage", method = RequestMethod.POST)
    public ModelAndView submitMessage(@ModelAttribute @Valid MessageForm messageForm, BindingResult result, Principal principal) {
        ModelAndView modelAndView= new ModelAndView("sendMessage");
        User sender = userService.getByName(principal.getName());
        addOtherUsernamesToModel(modelAndView, sender);
        if (result.hasErrors()) {
            return modelAndView;
        }
        User receiver = userService.getByName(messageForm.getReceiverUsername());
        Message message = new Message(sender, receiver, messageForm.getMessageContent(), messageForm.getTopic());
        messageService.save(message);
        modelAndView.addObject("message", "Successfully sent message");
        return modelAndView;
    }

    public void addOtherUsernamesToModel(ModelAndView modelAndView, User user) {
        List<String> usernames = new ArrayList<String>();
        for (User currentUser : userService.selectAllUsers()) {
            if (!currentUser.getUsername().equals(user.getUsername())) {
                usernames.add(currentUser.getUsername());
            }
        }
        modelAndView.addObject("usernames", usernames);
    }
}
