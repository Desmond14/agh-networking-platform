package com.io.traderbook.service;

import com.io.traderbook.model.Message;
import com.io.traderbook.repository.MessageRepository;

import java.util.List;

public class MessageService {
    private MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findByReceiverId(Long id) {
        return messageRepository.findByReceiverId(id);
    }

    public List<Message> findBySenderId(Long id) {
        return messageRepository.findBySenderId(id);
    }
}
