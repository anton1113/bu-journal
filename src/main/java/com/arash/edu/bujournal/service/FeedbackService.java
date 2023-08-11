package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.domain.Feedback;
import com.arash.edu.bujournal.domain.dto.FeedbackDTO;
import com.arash.edu.bujournal.error.BadRequestException;
import com.arash.edu.bujournal.error.InputValidationException;
import com.arash.edu.bujournal.mapper.FeedbackMapper;
import com.arash.edu.bujournal.repository.FeedbackRepository;
import com.arash.edu.bujournal.util.BuSecurityUtil;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class FeedbackService {

    private static final int MAX_FEEDBACK_LENGTH = 2048;
    private static final int MAX_DAILY_SESSION_FEEDBACK = 10;

    private final FeedbackMapper feedbackMapper;
    private final FeedbackRepository feedbackRepository;

    public Feedback postFeedback(@NonNull FeedbackDTO feedbackDTO) {
        log.info("Post feedback {}", feedbackDTO);

        validateFeedbackDTO(feedbackDTO);
        String sessionId = BuSecurityUtil.getSessionId();
        checkDuplication(feedbackDTO, sessionId);
        checkSessionSpam(sessionId);

        Feedback feedback = feedbackMapper.toModel(feedbackDTO);
        feedback.setSessionId(sessionId);
        feedback.setCreatedBy(getCreatedBy());

        return feedbackRepository.save(feedback);
    }

    private UUID getCreatedBy() {
        return BuSecurityUtil.getLoggedInUserIfPresent()
                .map(BuUser::getId)
                .orElse(null);
    }

    private void validateFeedbackDTO(FeedbackDTO feedbackDTO) {
        if (feedbackDTO.getText() == null || feedbackDTO.getText().isEmpty()) {
            throw new InputValidationException("Feedback is empty");
        }
        if (feedbackDTO.getText().length() > MAX_FEEDBACK_LENGTH) {
            throw new InputValidationException("Feedback length exceeds max value");
        }
    }

    private void checkDuplication(FeedbackDTO feedbackDTO, String sessionId) {
        if (feedbackRepository.existsByTextAndSessionId(feedbackDTO.getText(), sessionId)) {
            throw new BadRequestException("Duplicated feedback rejected");
        }
    }

    private void checkSessionSpam(String sessionId) {
        LocalDateTime startOfTheDay = LocalDate.now().atStartOfDay();
        int feedbackCount = feedbackRepository.countAllBySessionIdAndCreatedOnAfter(sessionId, startOfTheDay);
        if (feedbackCount > MAX_DAILY_SESSION_FEEDBACK) {
            throw new BadRequestException("Unable to save additional feedback");
        }
    }
}
