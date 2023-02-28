package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.repository.GroupRepository;
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
}
