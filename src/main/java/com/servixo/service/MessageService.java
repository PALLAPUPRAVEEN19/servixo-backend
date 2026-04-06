package com.servixo.service;

import com.servixo.entity.Message;
import com.servixo.entity.User;
import com.servixo.repository.MessageRepository;
import com.servixo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    // ================= SEND MESSAGE =================
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    // ================= GET ALL USER MESSAGES =================
    public List<Message> getMessages(Long userId) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        return messageRepository.findBySenderOrReceiver(user, user);
    }

    // ================= CHAT BETWEEN TWO USERS =================
    public List<Message> getConversation(Long user1Id, Long user2Id) {

        User user1 = userRepository.findById(user1Id).orElse(null);
        User user2 = userRepository.findById(user2Id).orElse(null);

        if (user1 == null || user2 == null) {
            throw new RuntimeException("User not found");
        }

        // messages both directions
        List<Message> sent = messageRepository.findBySenderAndReceiver(user1, user2);
        List<Message> received = messageRepository.findBySenderAndReceiver(user2, user1);

        sent.addAll(received);

        return sent;
    }
}