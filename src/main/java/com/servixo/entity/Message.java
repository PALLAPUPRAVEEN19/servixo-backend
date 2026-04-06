package com.servixo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    // ================= PRIMARY KEY =================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ================= MESSAGE CONTENT =================
    @Column(nullable = false)
    private String content;

    // ================= RELATIONSHIPS =================

    // Sender (User who sends message)
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    // Receiver (User who receives message)
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    // ================= TIMESTAMP =================
    private LocalDateTime timestamp;

    // ================= CONSTRUCTORS =================

    public Message() {}

    public Message(String content, User sender, User receiver, LocalDateTime timestamp) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
    }

    // ================= GETTERS & SETTERS =================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}