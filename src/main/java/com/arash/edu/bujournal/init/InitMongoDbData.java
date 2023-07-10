package com.arash.edu.bujournal.init;

import com.arash.edu.bujournal.domain.*;
import com.arash.edu.bujournal.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@ConditionalOnProperty(name = "bu.data.mongodb.recreate", havingValue = "true")
@Component
public class InitMongoDbData {

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

    @PostConstruct
    void initData() {
        log.info("Purging database before creating test data");
        attendanceRepository.deleteAll();
        groupRepository.deleteAll();
        lessonRepository.deleteAll();
        studentRepository.deleteAll();
        subjectRepository.deleteAll();
        teacherRepository.deleteAll();

        log.info("Initializing test teachers data");
        Teacher teacher1 = new Teacher(UUID.randomUUID(), "Артеменко", "Ольга", "Іванівна");
        Teacher teacher2 = new Teacher(UUID.randomUUID(), "Штерма", "Тетяна", "Василівна");
        Teacher teacher3 = new Teacher(UUID.randomUUID(), "Євдокименко", "Валерій", "Кирилович");
        teacherRepository.saveAll(List.of(teacher1, teacher2, teacher3));

        log.info("Initializing test group data");
        Group group1 = new Group(UUID.randomUUID(), "KM-501", teacher1.getId());
        Group group2 = new Group(UUID.randomUUID(), "ФM-501", teacher2.getId());
        Group group3 = new Group(UUID.randomUUID(), "АM-501", teacher3.getId());
        groupRepository.saveAll(List.of(group1, group2, group3));

        log.info("Initializing test student data");
        Student student1 = new Student(UUID.randomUUID(), group1.getId(), "Расщектаєв", "Антон", "Володимирович");
        Student student2 = new Student(UUID.randomUUID(), group1.getId(), "Дудник", "Роман", "Борисович");
        Student student3 = new Student(UUID.randomUUID(), group1.getId(), "Павлюк", "Іван", "Іванович");
        Student student4 = new Student(UUID.randomUUID(), group1.getId(), "Головач", "Іван", "Павлович");
        Student student5 = new Student(UUID.randomUUID(), group1.getId(), "Ящук", "Ігор", "Миколайович");
        studentRepository.saveAll(List.of(student1, student2, student3, student4, student5));

        log.info("Initializing test subject data");
        Subject subject1 = new Subject(UUID.randomUUID(), "Основи програмування", teacher1.getId(), group1.getId());
        Subject subject2 = new Subject(UUID.randomUUID(), "Менеджмент знань", teacher2.getId(), group1.getId());
        Subject subject3 = new Subject(UUID.randomUUID(), "Методологія наукових досліждень", teacher3.getId(), group1.getId());
        Subject subject4 = new Subject(UUID.randomUUID(), "Педагогіка та методика вищої школи", teacher2.getId(), group1.getId());
        Subject subject5 = new Subject(UUID.randomUUID(), "Нечіткі моделі та методи обчислювального інтелекту", teacher1.getId(), group1.getId());
        subjectRepository.saveAll(List.of(subject1, subject2, subject3, subject4, subject5));

        log.info("Initializing test lesson data");
        Lesson lesson1 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 3, 1), subject1.getId());
        Lesson lesson2 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 3, 8), subject1.getId());
        Lesson lesson3 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 3, 15), subject1.getId());
        Lesson lesson4 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 3, 22), subject1.getId());
        Lesson lesson5 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 3, 29), subject1.getId());
        Lesson lesson6 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 4, 4), subject1.getId());
        Lesson lesson7 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 4, 11), subject1.getId());
        Lesson lesson8 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 4, 18), subject1.getId());
        Lesson lesson9 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 4, 25), subject1.getId());
        Lesson lesson10 = new Lesson(UUID.randomUUID(), LocalDate.of(2023, 5, 2), subject1.getId());
        lessonRepository.saveAll(List.of(lesson1, lesson2, lesson3, lesson4, lesson5, lesson6, lesson7, lesson8, lesson9, lesson10));
    }
}
