package com.servixo.service;

import com.servixo.entity.Feedback;
import com.servixo.entity.Professional;
import com.servixo.repository.FeedbackRepository;
import com.servixo.repository.ProfessionalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private ProfessionalRepository professionalRepository;

    // ADD FEEDBACK
    public Feedback addFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    // GET BY PROFESSIONAL
    public List<Feedback> getFeedbackByProfessional(Long professionalId) {

        Professional pro = professionalRepository.findById(professionalId).orElse(null);

        return feedbackRepository.findByProfessional(pro);
    }

    // GET ALL
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }
}