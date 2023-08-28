package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends MongoRepository<Student, UUID> {

    List<Student> findAllByGroupIdOrderByLastNameAsc(UUID groupId);
}
