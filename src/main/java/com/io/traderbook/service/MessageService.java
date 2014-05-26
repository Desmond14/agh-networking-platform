package com.io.traderbook.service;

import com.io.traderbook.model.Message;
import com.io.traderbook.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findByReceiverId(Integer id) {
        return messageRepository.findByReceiverId(id);
    }

    public List<Message> findBySenderId(Integer id) {
        return messageRepository.findBySenderId(id);
    }
}
