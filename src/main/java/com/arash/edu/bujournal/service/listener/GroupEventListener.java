package com.arash.edu.bujournal.service.listener;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.domain.Subject;
import com.arash.edu.bujournal.repository.SubjectRepository;
import com.arash.edu.bujournal.service.StudentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class GroupEventListener {

    private final StudentService studentService;
    private final SubjectRepository subjectRepository;

    public void onGroupDeleted(@NonNull UUID groupId) {
        log.info("Received group_deleted event, groupId={}", groupId);
        studentService.findAllByGroupId(groupId).stream()
                        .map(Student::getId).forEach(studentService::deleteById);

        List<Subject> groupSubjects = subjectRepository.findAllByGroupId(groupId);
        if (!CollectionUtils.isEmpty(groupSubjects)) {
            log.info("Group [{}] subjects {}", groupId, groupSubjects);
            groupSubjects.forEach(subject -> subject.setGroupId(null));
            subjectRepository.saveAll(groupSubjects);;
        }
    }
}
