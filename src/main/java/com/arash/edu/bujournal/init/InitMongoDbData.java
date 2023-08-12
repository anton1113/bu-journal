package com.arash.edu.bujournal.init;

import com.arash.edu.bujournal.domain.*;
import com.arash.edu.bujournal.domain.enums.BuUserRole;
import com.arash.edu.bujournal.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@ConditionalOnProperty(name = "bu.data.mongodb.test-data.init", havingValue = "true")
@Component
public class InitMongoDbData {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private LessonRepository lessonRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private SourceRepository sourceRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private BuUserRepository buUserRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    void initData() {
        purgeTestData();
        createTestData();
    }

    private void purgeTestData() {
        log.info("Purging test data");
        attendanceRepository.deleteAll();
        groupRepository.deleteAll();
        lessonRepository.deleteAll();
        studentRepository.deleteAll();
        subjectRepository.deleteAll();
        teacherRepository.deleteAll();
        adminRepository.deleteAll();
        sourceRepository.deleteAll();
        assignmentRepository.deleteAll();
        buUserRepository.deleteAll();
        feedbackRepository.deleteAll();
    }

    private void createTestData() {
        log.info("Initializing test teachers data");
        Teacher teacher1 = new Teacher(randomUUID(), "Морараш", "Галина", "Володимирівна");
        Teacher teacher2 = new Teacher(randomUUID(), "Вершигора", "Валерія", "Григорівна");
        Teacher teacher3 = new Teacher(randomUUID(), "Кушнір", "Ярослав", "Вікторович");
        Teacher teacher4 = new Teacher(randomUUID(), "Руснак", "Леся", "Михайлівна");
        Teacher teacher5 = new Teacher(randomUUID(), "Веклич", "Мар'яна", "Миколаївна");
        Teacher teacher6 = new Teacher(randomUUID(), "Боренко", "Валерій", "Борисович");
        Teacher teacher7 = new Teacher(randomUUID(), "Іванчен", "Ілля", "Віталійович");
        Teacher teacher8 = new Teacher(randomUUID(), "Штерма", "Тетяна", "Василівна");
        teacherRepository.saveAll(List.of(teacher1, teacher2, teacher3, teacher4, teacher5, teacher6, teacher7, teacher8));

        BuUser teacherUser = new BuUser(UUID.randomUUID(), teacher1.getId(), "teacher", passwordEncoder.encode("teacher"), BuUserRole.TEACHER);
        buUserRepository.save(teacherUser);

        log.info("Initializing test group data");
        Group group1 = new Group(randomUUID(), "KM-501", teacher8.getId());
        groupRepository.save(group1);

        log.info("Initializing test student data");
        Student student1 = new Student(randomUUID(), group1.getId(), "Ботнар" ,"Денис", "Русланович");
        Student student2 = new Student(randomUUID(), group1.getId(), "Бурса" ,"Валентин", "Валентинович");
        Student student3 = new Student(randomUUID(), group1.getId(), "Гончарук" ,"Олексій", "Романович");
        Student student4 = new Student(randomUUID(), group1.getId(), "Гринчук" ,"Назар", "Васильович");
        Student student5 = new Student(randomUUID(), group1.getId(), "Гусениця" ,"Гліб", "Сергійович");
        Student student6 = new Student(randomUUID(), group1.getId(), "Зеліско" ,"Станіслав", "Васильович");
        Student student7 = new Student(randomUUID(), group1.getId(), "Калинюк" ,"Ілля" ,"Дмитрович");
        Student student8 = new Student(randomUUID(), group1.getId(), "Кременецький" ,"Михайло" ,"Русланович");
        Student student9 = new Student(randomUUID(), group1.getId(), "Ланівський" ,"Микола" ,"Віталійович");
        Student student10 = new Student(randomUUID(), group1.getId(), "Луцкевіч" ,"Кирил" ,"Станісоавович");
        Student student11 = new Student(randomUUID(), group1.getId(), "Максимчук" ,"Денис" ,"Вадимович");
        Student student12 = new Student(randomUUID(), group1.getId(), "Мельник" ,"Артем" ,"Олегович");
        Student student13 = new Student(randomUUID(), group1.getId(), "Микитюк" ,"Аліса" ,"Теофілівна");
        Student student14 = new Student(randomUUID(), group1.getId(), "Мінтянський" ,"Євгеній" ,"Іларійович");
        Student student15 = new Student(randomUUID(), group1.getId(), "Неборак" ,"Євгеній" ,"Віталійович");
        Student student16 = new Student(randomUUID(), group1.getId(), "Радукан" ,"Яна" ,"Пантелеймонівна");
        Student student17 = new Student(randomUUID(), group1.getId(), "Савчук" ,"Максим" ,"Анатолійович");
        Student student18 = new Student(randomUUID(), group1.getId(), "Семенюк" ,"Дар’я" ,"Миколаївна");
        Student student19 = new Student(randomUUID(), group1.getId(), "Синиця" ,"Станіслав" ,"Юрійович");
        Student student20 = new Student(randomUUID(), group1.getId(), "Ткач" ,"Дмитро" ,"Михайлович");
        Student student21 = new Student(randomUUID(), group1.getId(), "Туряк" ,"Владислав" ,"Дмитрович");
        Student student22 = new Student(randomUUID(), group1.getId(), "Федорюк" ,"Назар-Степан" ,"Сергійович");
        studentRepository.saveAll(List.of(student1, student2, student3, student4, student5, student6, student7, student8,
                student9, student10, student11, student12, student13, student14, student15, student16, student17, student18,
                student19, student20, student21, student22));

        BuUser studentUser = new BuUser(UUID.randomUUID(), student1.getId(), "student", passwordEncoder.encode("student"), BuUserRole.STUDENT);
        buUserRepository.save(studentUser);

        log.info("Initializing test admin data");
        Admin admin = new Admin(UUID.randomUUID(), "Rash", "Anton", null);
        adminRepository.save(admin);

        BuUser adminUser = new BuUser(UUID.randomUUID(), admin.getId(), "admin", passwordEncoder.encode("admin"), BuUserRole.ADMIN);
        buUserRepository.save(adminUser);

        log.info("Initializing test subject data");
        Subject subject1 = new Subject(randomUUID(), "Українська мова", teacher1.getId(), group1.getId());
        Subject subject2 = new Subject(randomUUID(), "Українська література", teacher1.getId(), group1.getId());
        Subject subject3 = new Subject(randomUUID(), "Зарубіжна література", teacher1.getId(), group1.getId());
        Subject subject4 = new Subject(randomUUID(), "Математика", teacher2.getId(), group1.getId());
        Subject subject5 = new Subject(randomUUID(), "Історія: Україна і світ", teacher3.getId(), group1.getId());
        Subject subject6 = new Subject(randomUUID(), "Громадянська освіта* (Соціологія, Безпека життєдіяльності та цивільний захист)", teacher4.getId(), group1.getId());
        Subject subject7 = new Subject(randomUUID(), "Природничі науки", teacher5.getId(), group1.getId());
        Subject subject8 = new Subject(randomUUID(), "Іноземна мова", teacher6.getId(), group1.getId());
        Subject subject9 = new Subject(randomUUID(), "Фізична культура", teacher7.getId(), group1.getId());
        Subject subject10 = new Subject(randomUUID(), "Захист України", teacher3.getId(), group1.getId());
        subjectRepository.saveAll(List.of(subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8, subject9, subject10));
    }
}
