package com.servixo.controller;

import com.servixo.entity.Feedback;
import com.servixo.service.FeedbackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin("*")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // ================= ADD FEEDBACK =================
    @PostMapping
    public Feedback addFeedback(@RequestBody Feedback feedback) {
        return feedbackService.addFeedback(feedback);
    }

    // ================= GET FEEDBACK BY PROFESSIONAL =================
    @GetMapping("/professional/{id}")
    public List<Feedback> getFeedbackByProfessional(@PathVariable Long id) {
        return feedbackService.getFeedbackByProfessional(id);
    }

    // ================= GET ALL FEEDBACK =================
    @GetMapping
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }
}