package com.arash.edu.bujournal.mapper;

import com.arash.edu.bujournal.domain.Subject;
import com.arash.edu.bujournal.dtos.SubjectDTO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class SubjectsMapper {

    public List<SubjectDTO> toDTOList(@NonNull List<Subject> subjects) {
        return subjects.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SubjectDTO toDTO(@NonNull Subject subject) {
        SubjectDTO subjectDTO = new SubjectDTO();
        subjectDTO.setId(subject.getId());
        subjectDTO.setName(subject.getName());
        subjectDTO.setTeacherId(subject.getTeacherId());
        subjectDTO.setGroupId(subject.getGroupId());
        return subjectDTO;
    }

    public Subject toModel(@NonNull SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setId(subjectDTO.getId());
        subject.setName(subjectDTO.getName());
        subject.setTeacherId(subjectDTO.getTeacherId());
        subject.setGroupId(subjectDTO.getGroupId());
        return subject;
    }
}
