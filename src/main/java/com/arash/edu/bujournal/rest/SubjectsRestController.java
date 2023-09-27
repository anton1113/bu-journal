package com.arash.edu.bujournal.rest;

import com.arash.edu.bujournal.api.SubjectsApi;
import com.arash.edu.bujournal.domain.Subject;
import com.arash.edu.bujournal.dtos.SubjectDTO;
import com.arash.edu.bujournal.mapper.SubjectsMapper;
import com.arash.edu.bujournal.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class SubjectsRestController implements SubjectsApi {

    private final SubjectService subjectService;
    private final SubjectsMapper subjectsMapper;

    @Override
    public List<SubjectDTO> getAllSubjects(UUID teacherId, UUID groupId) {
        List<Subject> subjects;
        if (teacherId == null && groupId == null) {
            subjects = subjectService.findAll();
        } else if (teacherId == null) {
            subjects = subjectService.findAllByGroupId(groupId);
        } else if (groupId == null) {
            subjects = subjectService.findAllByTeacherId(teacherId);
        } else {
            subjects = subjectService.findAllByTeacherIdAndGroupId(teacherId, groupId);
        }
        return subjectsMapper.toDTOList(subjects);
    }

    @Override
    public SubjectDTO postSubject(SubjectDTO subjectDTO) {
        Subject subject = subjectsMapper.toModel(subjectDTO);
        Subject newSubject = subjectService.add(subject);
        return subjectsMapper.toDTO(newSubject);
    }

    @Override
    public SubjectDTO deleteSubject(UUID subjectId) {
        Subject deletedSubject = subjectService.deleteSubject(subjectId);
        return subjectsMapper.toDTO(deletedSubject);
    }
}
