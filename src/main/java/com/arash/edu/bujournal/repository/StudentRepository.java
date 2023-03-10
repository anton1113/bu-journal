package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findAllByGroupId(Long groupId);
}
