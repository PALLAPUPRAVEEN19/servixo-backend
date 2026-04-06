package com.servixo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.servixo.entity.Message;
import com.servixo.entity.User;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // Get all messages where user is sender OR receiver
    List<Message> findBySenderOrReceiver(User sender, User receiver);

    // (Optional - advanced chat between two users)
    List<Message> findBySenderAndReceiver(User sender, User receiver);
}