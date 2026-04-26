package com.arash.edu.bujournal.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    @ModelAttribute("currentRequestUri")
    public String currentRequestUri(HttpServletRequest request) {
        return request.getRequestURI();
    }
}
