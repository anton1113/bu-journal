package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.GroupRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> findAll() {
        log.info("Find all groups");
        return groupRepository.findAll();
    }

    public Group add(@NonNull Group group) {
        log.info("Add group {}", group);
        return groupRepository.save(group);
    }

    public Group findById(@NonNull Long id) {
        log.info("Find group by id [{}]", id);
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Group not found by id " + id));
    }

    public Group findByNullableId(Long id) {
        log.info("Find group by nullable id [{}]", id);
        if (id == null) {
            return null;
        }
        return groupRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Group not found by id " + id));
    }

    public void deleteById(@NonNull Long groupId) {
        log.info("Delete group by id [{}]", groupId);
        groupRepository.deleteById(groupId);
    }
}
