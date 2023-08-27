package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Assignment;
import com.arash.edu.bujournal.domain.Attachment;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.AssignmentRepository;
import com.arash.edu.bujournal.service.listener.AssignmentEventListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AttachmentService attachmentService;
    private final AssignmentEventListener assignmentEventListener;

    public Assignment findById(@NonNull UUID id) {
        log.info("Find assignment by id [{}]", id);
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Assignment not found by id " + id));
    }

    public List<Assignment> findAllByLessonId(@NonNull UUID lessonId) {
        log.info("Find all assignments by lessonId {}", lessonId);
        return assignmentRepository.findAllByLessonId(lessonId);
    }

    public Assignment addAssignment(@NonNull Assignment assignment, @NonNull UUID lessonId) {
        log.info("Adding assignment {}", assignment);

        if (assignment.getId() == null) {
            assignment.setId(randomUUID());
        }
        assignment.setLessonId(lessonId);

        if (assignment.getFile() != null && !assignment.getFile().isEmpty()) {
            Attachment attachment = attachmentService.addAttachment(assignment.getFile(), assignment.getId());
            assignment.setAttachmentId(attachment.getId());
            assignment.setAttachmentName(attachment.getName());
        }

        return assignmentRepository.save(assignment);
    }

    public Assignment editAssignment(@NonNull UUID id, @NonNull Assignment assignment) {
        log.info("Editing assignment with {}, {}", id, assignment);
        if (!assignmentRepository.existsById(id)) {
            throw new NotFoundException("Assignment with id " + id + "not found, unable to edit");
        }
        assignment.setId(id);

        if (assignment.getFile() != null && !assignment.getFile().isEmpty()) {
            // delete old attachment
            assignmentRepository.findById(id)
                    .map(Assignment::getAttachmentId)
                    .ifPresent(attachmentService::deleteAttachment);

            // save new attachment
            Attachment attachment = attachmentService.addAttachment(assignment.getFile(), assignment.getId());
            assignment.setAttachmentId(attachment.getId());
            assignment.setAttachmentName(attachment.getName());
        }

        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(@NonNull UUID id) {
        log.info("Delete assignment by id [{}]", id);
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Assignment with id " + id + " not found"));
        assignmentRepository.delete(assignment);
        assignmentEventListener.onAssignmentDeleted(assignment);
    }
}
