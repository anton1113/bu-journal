package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
