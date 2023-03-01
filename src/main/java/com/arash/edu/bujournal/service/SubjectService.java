package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Subject;
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

    public Subject add(@NonNull Subject subject) {
        log.info("Add subject {}", subject);
        return subjectRepository.save(subject);
    }
}
