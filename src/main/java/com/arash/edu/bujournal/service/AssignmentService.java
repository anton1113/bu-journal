package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Assignment;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.AssignmentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

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
            assignment.setId(UUID.randomUUID());
        }
        assignment.setLessonId(lessonId);
        return assignmentRepository.save(assignment);
    }

    public Assignment editAssignment(@NonNull UUID id, @NonNull Assignment assignment) {
        log.info("Editing assignment with {}, {}", id, assignment);
        if (!assignmentRepository.existsById(id)) {
            throw new NotFoundException("Assignment with id " + id + "not found, unable to edit");
        }
        assignment.setId(id);
        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(@NonNull UUID id) {
        log.info("Delete assignment by id [{}]", id);
        assignmentRepository.deleteById(id);
    }
}
