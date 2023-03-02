package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findAllByGroupId(Long groupId);
}
