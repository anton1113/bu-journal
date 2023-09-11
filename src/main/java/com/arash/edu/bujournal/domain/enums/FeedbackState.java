package com.arash.edu.bujournal.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum FeedbackState {

    ACTIVE("Active"),
    RESOLVED("Resolved"),
    REJECTED("Rejected");

    @Getter
    private final String value;
}
