package com.arash.edu.bujournal;

import com.arash.edu.bujournal.init.TestContainersMongoDbInitializer;
import com.arash.edu.bujournal.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(initializers = TestContainersMongoDbInitializer.class)
@ActiveProfiles("mongo-test")
public abstract class MongoDbIntegrationTest extends BaseIntegrationTest {

    @Autowired
    protected AttendanceRepository attendanceRepository;
    @Autowired
    protected GroupRepository groupRepository;
    @Autowired
    protected LessonRepository lessonRepository;
    @Autowired
    protected StudentRepository studentRepository;
    @Autowired
    protected SubjectRepository subjectRepository;
    @Autowired
    protected TeacherRepository teacherRepository;

    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        attendanceRepository.deleteAll();
        groupRepository.deleteAll();
        lessonRepository.deleteAll();
        studentRepository.deleteAll();
        subjectRepository.deleteAll();
        teacherRepository.deleteAll();
    }
}
