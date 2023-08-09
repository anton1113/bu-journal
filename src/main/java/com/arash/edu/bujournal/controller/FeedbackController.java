package com.arash.edu.bujournal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FeedbackController {

    @GetMapping(value = {"/feedback"})
    public String showFeedbackPage() {
        return "feedback";
    }
}
