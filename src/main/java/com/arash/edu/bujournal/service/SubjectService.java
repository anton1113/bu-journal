package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Subject;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.SubjectRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        log.info("Find all subjects");
        return subjectRepository.findAll();
    }

    public List<Subject> findAllByGroupId(@NonNull UUID groupId) {
        log.info("Find all subjects by group id [{}]", groupId);
        return subjectRepository.findAllByGroupId(groupId);
    }

    public Subject findById(@NonNull UUID id) {
        log.info("Find subject by id [{}]", id);
        return subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject not found by id"));
    }

    public Subject add(@NonNull Subject subject) {
        log.info("Add subject {}", subject);
        if (subject.getId() == null) {
            subject.setId(UUID.randomUUID());
        }
        return subjectRepository.save(subject);
    }

    public void deleteSubject(@NonNull UUID id) {
        log.info("Delete subject by id [{}]", id);
        subjectRepository.deleteById(id);
    }

    public Subject editSubject(@NonNull UUID id, @NonNull Subject subject) {
        log.info("Editing subject with {}, {}", id, subject);
        if (!subjectRepository.existsById(id)) {
            throw new NotFoundException("Subject with id " + id + "not found, unable to edit");
        }
        subject.setId(id);
        return subjectRepository.save(subject);
    }
}
