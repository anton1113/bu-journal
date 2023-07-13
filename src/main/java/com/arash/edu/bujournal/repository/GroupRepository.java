package com.arash.edu.bujournal.repository;

import com.arash.edu.bujournal.domain.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends MongoRepository<Group, UUID> {

    List<Group> findAllByCuratorId(UUID curatorId);
}
