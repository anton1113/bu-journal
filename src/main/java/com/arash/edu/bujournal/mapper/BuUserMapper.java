package com.arash.edu.bujournal.mapper;

import com.arash.edu.bujournal.domain.BuUser;
import com.arash.edu.bujournal.dtos.BuUserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BuUserMapper {

    public BuUserDTO toDTO(BuUser buUser) {
        BuUserDTO buUserDTO = new BuUserDTO();
        buUserDTO.setId(buUser.getId());
        buUserDTO.setExternalId(buUser.getExternalId());
        buUserDTO.setUsername(buUser.getUsername());
        buUserDTO.setRole(buUser.getRole().getValue());
        return buUserDTO;
    }
}
