package com.arash.edu.bujournal.service.listener;

import com.arash.edu.bujournal.domain.Assignment;
import com.arash.edu.bujournal.service.AttachmentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AssignmentEventListener {

    private final AttachmentService attachmentService;

    public void onAssignmentDeleted(@NonNull Assignment assignment) {
        log.info("Received assignment_deleted event, assignmentId={}", assignment.getId());

        if (assignment.getAttachmentId() != null) {
            attachmentService.deleteAttachment(assignment.getAttachmentId());
        }
    }
}
