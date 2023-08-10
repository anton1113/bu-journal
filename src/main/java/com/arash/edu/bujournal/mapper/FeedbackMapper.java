package com.arash.edu.bujournal.mapper;

import com.arash.edu.bujournal.domain.Feedback;
import com.arash.edu.bujournal.domain.dto.FeedbackDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.util.UUID.randomUUID;

@RequiredArgsConstructor
@Component
public class FeedbackMapper {

    public Feedback toModel(FeedbackDTO feedbackDTO) {
        Feedback feedback = new Feedback();
        feedback.setId(randomUUID());
        feedback.setText(feedbackDTO.getText());
        feedback.setCreatedOn(LocalDateTime.now());
        return feedback;
    }
}
