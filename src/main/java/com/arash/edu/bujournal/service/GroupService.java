package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.GroupRepository;
import com.arash.edu.bujournal.service.listener.GroupEventListener;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupEventListener groupEventListener;

    public List<Group> findAll() {
        log.info("Find all groups");
        return groupRepository.findAll();
    }

    public List<Group> findAllByCuratorId(@NonNull UUID curatorId) {
        log.info("Find all groups by curatorId {}", curatorId);
        return groupRepository.findAllByCuratorId(curatorId);
    }

    public Group findById(@NonNull UUID id) {
        log.info("Find group by id [{}]", id);
        return findGroup(id);
    }

    public Group findByNullableId(UUID id) {
        log.info("Find group by nullable id [{}]", id);
        if (id == null) {
            return null;
        }
        return groupRepository.findById(id)
                .orElse(null);
    }

    public Group add(@NonNull Group group) {
        log.info("Add group {}", group);
        if (group.getId() == null) {
            group.setId(UUID.randomUUID());
        }
        return groupRepository.save(group);
    }

    public Group editGroup(@NonNull UUID id, @NonNull Group group) {
        log.info("Editing group with {}, {}", id, group);
        if (!groupRepository.existsById(id)) {
            throw new NotFoundException("Group with id " + id + "not found, unable to edit");
        }
        group.setId(id);
        return groupRepository.save(group);
    }

    public Group deleteById(@NonNull UUID groupId) {
        log.info("Delete group by id [{}]", groupId);
        Group group = findGroup(groupId);
        groupRepository.delete(group);
        groupEventListener.onGroupDeleted(groupId);
        return group;
    }

    private Group findGroup(@NonNull UUID id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Group not found by id " + id));
    }
}
