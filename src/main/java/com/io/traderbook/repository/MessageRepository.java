package com.io.traderbook.repository;

import com.io.traderbook.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {

    @Query(value = "select m from Message m where m.receiver.id = ?1")
    List<Message> findByReceiverId(Long id);

    @Query(value = "select m from Message m where m.sender.id = ?1")
    List<Message> findBySenderId(Long id);
}
