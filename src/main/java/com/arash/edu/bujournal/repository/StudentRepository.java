package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findStudentByFirstNameAndLastName(String firstName, String lastName);
}
