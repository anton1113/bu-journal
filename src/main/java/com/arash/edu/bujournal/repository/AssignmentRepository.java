package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Assignment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssignmentRepository extends MongoRepository<Assignment, UUID> {
}
