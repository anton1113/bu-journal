package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.dto.FeedbackDTO;
import com.arash.edu.bujournal.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping(value = {"/feedback"})
    public String showFeedbackPage(Model model) {
        FeedbackDTO feedbackDraft = new FeedbackDTO();
        model.addAttribute("feedbackDraft", feedbackDraft);
        return "feedback";
    }

    @PostMapping("/feedback")
    public String postFeedback(@ModelAttribute FeedbackDTO feedbackDTO) {
        feedbackService.postFeedback(feedbackDTO);
        return "redirect:/feedback";
    }
}
