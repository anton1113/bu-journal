package com.arash.edu.bujournal.service.listener;

import com.arash.edu.bujournal.domain.Group;
import com.arash.edu.bujournal.domain.Subject;
import com.arash.edu.bujournal.domain.Teacher;
import com.arash.edu.bujournal.repository.GroupRepository;
import com.arash.edu.bujournal.repository.SubjectRepository;
import com.arash.edu.bujournal.service.auth.BuUserRegisterService;
import com.arash.edu.bujournal.service.auth.BuUserService;
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
public class TeacherEventListener {

    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final BuUserRegisterService buUserRegisterService;
    private final BuUserService buUserService;

    public void onTeacherDeleted(@NonNull UUID teacherId) {
        log.info("Received teacher_deleted event, teacherId={}", teacherId);

        List<Group> teacherGroups = groupRepository.findAllByCuratorId(teacherId);
        if (!CollectionUtils.isEmpty(teacherGroups)) {
            log.info("Teacher [{}] is not a curator of groups {}", teacherId, teacherGroups);
            teacherGroups.forEach(group -> group.setCuratorId(null));
            groupRepository.saveAll(teacherGroups);
        }

        List<Subject> teacherSubjects = subjectRepository.findAllByTeacherId(teacherId);
        if (!CollectionUtils.isEmpty(teacherSubjects)) {
            log.info("Teacher [{}] has no any subjects {}", teacherId, teacherSubjects);
            teacherSubjects.forEach(subject -> subject.setTeacherId(null));
            subjectRepository.saveAll(teacherSubjects);;
        }

        buUserService.deleteUserByExternalId(teacherId);
    }

    public void onTeacherCreated(@NonNull Teacher teacher) {
        log.info("Received teacher_created event, {}", teacher);
        buUserRegisterService.registerTeacher(teacher);
    }
}
