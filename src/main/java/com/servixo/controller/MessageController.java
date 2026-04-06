package com.servixo.controller;

import com.servixo.entity.Message;
import com.servixo.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/user/{id}")
    public List<Message> getMessages(@PathVariable Long id) {
        return messageService.getMessages(id);
    }
}