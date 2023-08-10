package com.arash.edu.bujournal.init;

import com.arash.edu.bujournal.domain.*;
import com.arash.edu.bujournal.domain.enums.LessonType;
import com.arash.edu.bujournal.repository.*;
import com.arash.edu.bujournal.service.auth.BuUserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@ConditionalOnProperty(name = "bu.data.mongodb.recreate", havingValue = "true")
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
    private BuUserRepository buUserRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private BuUserRegisterService buUserRegisterService;

    @PostConstruct
    void initData() {
        log.info("Purging database before creating test data");
        attendanceRepository.deleteAll();
        groupRepository.deleteAll();
        lessonRepository.deleteAll();
        studentRepository.deleteAll();
        subjectRepository.deleteAll();
        teacherRepository.deleteAll();
        adminRepository.deleteAll();
        sourceRepository.deleteAll();
        buUserRepository.deleteAll();
        feedbackRepository.deleteAll();

        log.info("Initializing test teachers data");
        Teacher teacher1 = new Teacher(randomUUID(), "Артеменко", "Ольга", "Іванівна");
        Teacher teacher2 = new Teacher(randomUUID(), "Штерма", "Тетяна", "Василівна");
        Teacher teacher3 = new Teacher(randomUUID(), "Євдокименко", "Валерій", "Кирилович");
        teacherRepository.saveAll(List.of(teacher1, teacher2, teacher3));

        log.info("Registering users for teachers");
        buUserRegisterService.registerTeacher(teacher1);
        buUserRegisterService.registerTeacher(teacher2);
        buUserRegisterService.registerTeacher(teacher3);

        log.info("Initializing test group data");
        Group group1 = new Group(randomUUID(), "KM-501", teacher1.getId());
        Group group2 = new Group(randomUUID(), "ФM-501", teacher2.getId());
        Group group3 = new Group(randomUUID(), "АM-501", teacher3.getId());
        groupRepository.saveAll(List.of(group1, group2, group3));

        log.info("Initializing test student data");
        Student student1 = new Student(randomUUID(), group1.getId(), "Расщектаєв", "Антон", "Володимирович");
        Student student2 = new Student(randomUUID(), group1.getId(), "Дудник", "Роман", "Борисович");
        Student student3 = new Student(randomUUID(), group1.getId(), "Павлюк", "Іван", "Іванович");
        Student student4 = new Student(randomUUID(), group1.getId(), "Головач", "Іван", "Павлович");
        Student student5 = new Student(randomUUID(), group1.getId(), "Ящук", "Ігор", "Миколайович");
        studentRepository.saveAll(List.of(student1, student2, student3, student4, student5));

        log.info("Registering users for students");
        buUserRegisterService.registerStudent(student1);
        buUserRegisterService.registerStudent(student2);
        buUserRegisterService.registerStudent(student3);
        buUserRegisterService.registerStudent(student4);
        buUserRegisterService.registerStudent(student5);

        log.info("Initializing test admin data");
        Admin admin = new Admin(UUID.randomUUID(), "Rash", "Anton", null);
        adminRepository.save(admin);

        log.info("Registering users for admins");
        buUserRegisterService.registerAdmin(admin);

        log.info("Initializing test subject data");
        Subject subject1 = new Subject(randomUUID(), "Основи програмування", teacher1.getId(), group1.getId());
        Subject subject2 = new Subject(randomUUID(), "Менеджмент знань", teacher2.getId(), group1.getId());
        Subject subject3 = new Subject(randomUUID(), "Методологія наукових досліждень", teacher3.getId(), group1.getId());
        Subject subject4 = new Subject(randomUUID(), "Педагогіка та методика вищої школи", teacher2.getId(), group1.getId());
        Subject subject5 = new Subject(randomUUID(), "Нечіткі моделі та методи обчислювального інтелекту", teacher1.getId(), group1.getId());
        subjectRepository.saveAll(List.of(subject1, subject2, subject3, subject4, subject5));

        log.info("Initializing test lesson data");
        Lesson lesson1 = new Lesson(randomUUID(), "Вступ до основ програмування", LessonType.LECTURE, LocalDate.of(2023, 3, 1), subject1.getId());
        Lesson lesson2 = new Lesson(randomUUID(), "Типи даних", LessonType.LECTURE, LocalDate.of(2023, 3, 8), subject1.getId());
        Lesson lesson3 = new Lesson(randomUUID(), "Масиви", LessonType.LECTURE, LocalDate.of(2023, 3, 15), subject1.getId());
        Lesson lesson4 = new Lesson(randomUUID(), "Вступ до ООП, Об'єкти і класи", LessonType.LECTURE, LocalDate.of(2023, 3, 22), subject1.getId());
        Lesson lesson5 = new Lesson(randomUUID(), "Основні принципи ООП", LessonType.LECTURE, LocalDate.of(2023, 3, 29), subject1.getId());
        Lesson lesson6 = new Lesson(randomUUID(), "Встановлення середовища розробки Java. Hello world!", LessonType.SEMINAR, LocalDate.of(2023, 4, 4), subject1.getId());
        Lesson lesson7 = new Lesson(randomUUID(), "Робота з примітивними типами. Калькулятор.", LessonType.SEMINAR, LocalDate.of(2023, 4, 11), subject1.getId());
        Lesson lesson8 = new Lesson(randomUUID(), "Робота з масивами. Операції над матрицями.", LessonType.SEMINAR, LocalDate.of(2023, 4, 18), subject1.getId());
        Lesson lesson9 = new Lesson(randomUUID(), "Робота з класами. Модулювання уявних чисел.", LessonType.SEMINAR, LocalDate.of(2023, 4, 25), subject1.getId());
        Lesson lesson10 = new Lesson(randomUUID(), "Реалізація поліморфізму. Шаблон 'Абстрактна фабрика'", LessonType.SEMINAR, LocalDate.of(2023, 5, 2), subject1.getId());
        Lesson lesson11 = new Lesson(randomUUID(), "Підсумкова контрольна робота", null, LocalDate.of(2023, 5, 9), subject1.getId());
        lessonRepository.saveAll(List.of(lesson1, lesson2, lesson3, lesson4, lesson5, lesson6, lesson7, lesson8, lesson9, lesson10, lesson11));

        log.info("Initializing test source data");
        Source source1 = new Source(randomUUID(), lesson1.getId(), "Методичка", "https://google.com/", null);
        Source source2 = new Source(randomUUID(), lesson1.getId(), "Підручник", "https://www.wikipedia.org/", null);
        Source source3 = new Source(randomUUID(), lesson1.getId(), "Додаткові матеріали", "https://github.com/", null);
        sourceRepository.saveAll(List.of(source1, source2, source3));
    }
}
