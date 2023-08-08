package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.BuUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuUserRepository extends MongoRepository<BuUser, UUID> {

    BuUser findByUsername(String username);
}
