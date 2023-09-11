package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Feedback;
import com.arash.edu.bujournal.domain.dto.FeedbackDTO;
import com.arash.edu.bujournal.domain.enums.FeedbackState;
import com.arash.edu.bujournal.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class FeedbackController {

    private final FeedbackService feedbackService;

    @GetMapping("/feedback")
    public String showFeedbackPage(Model model) {
        FeedbackDTO feedbackDraft = new FeedbackDTO();
        model.addAttribute("feedbackDraft", feedbackDraft);
        return "feedback";
    }

    @PostMapping("/feedback")
    public String postFeedback(@ModelAttribute FeedbackDTO feedbackDTO, RedirectAttributes redirectAttributes) {
        try {
            feedbackService.postFeedback(feedbackDTO);
            redirectAttributes.addFlashAttribute("infoMessage", "Feedback has been sent successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error occurred during sending feedback: " + e.getMessage());
        }

        return "redirect:/feedback";
    }

    @GetMapping("/feedback/{id}")
    public String setFeedbackState(@PathVariable UUID id, @RequestParam FeedbackState state) {
        feedbackService.setState(id, state);
        return "redirect:/feedbacks";
    }

    @GetMapping("/feedbacks")
    public String showAllFeedbacks(Model model) {
        List<Feedback> feedbacks = feedbackService.getActiveFeedbacks();
        model.addAttribute("feedbacks", feedbacks);
        return "feedbacks";
    }
}
