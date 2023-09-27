package com.arash.edu.bujournal.mapper;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.dtos.GroupDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GroupsMapper {

    public List<GroupDTO> toDTOList(@NonNull List<Group> groups) {
        return groups.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public GroupDTO toDTO(@NonNull Group group) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setId(group.getId());
        groupDTO.setName(group.getName());
        groupDTO.setCuratorId(group.getCuratorId());
        return groupDTO;
    }

    public Group toModel(@NonNull GroupDTO groupDTO) {
        Group group = new Group();
        group.setId(groupDTO.getId());
        group.setName(groupDTO.getName());
        group.setCuratorId(groupDTO.getCuratorId());
        return group;
    }
}
