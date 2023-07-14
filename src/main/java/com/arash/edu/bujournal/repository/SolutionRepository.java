package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Solution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SolutionRepository extends MongoRepository<Solution, UUID> {
}
