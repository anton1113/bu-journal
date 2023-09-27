package com.arash.edu.bujournal.rest;

import com.arash.edu.bujournal.api.GroupsApi;
import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.dtos.GroupDTO;
import com.arash.edu.bujournal.mapper.GroupsMapper;
import com.arash.edu.bujournal.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class GroupsRestController implements GroupsApi {

    private final GroupService groupService;
    private final GroupsMapper groupsMapper;

    @Override
    public List<GroupDTO> getAllGroups() {
        List<Group> groups = groupService.findAll();
        return groupsMapper.toDTOList(groups);
    }

    @Override
    public GroupDTO postGroup(GroupDTO groupDTO) {
        Group group = groupsMapper.toModel(groupDTO);
        Group newGroup = groupService.add(group);
        return groupsMapper.toDTO(newGroup);
    }

    @Override
    public GroupDTO deleteGroup(UUID groupId) {
        Group deletedGroup = groupService.deleteById(groupId);
        return groupsMapper.toDTO(deletedGroup);
    }
}
