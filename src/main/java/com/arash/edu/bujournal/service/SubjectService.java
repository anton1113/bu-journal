package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Subject;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.SubjectRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public List<Subject> findAll() {
        log.info("Find all subjects");
        return subjectRepository.findAll();
    }

    public Subject findByid(@NonNull Long id) {
        log.info("Find subject by id [{}]", id);
        return subjectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subject not found by id"));
    }

    public Subject add(@NonNull Subject subject) {
        log.info("Add subject {}", subject);
        return subjectRepository.save(subject);
    }
}
