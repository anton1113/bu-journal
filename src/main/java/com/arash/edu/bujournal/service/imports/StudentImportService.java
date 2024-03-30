package com.arash.edu.bujournal.service.imports;

import com.arash.edu.bujournal.domain.Student;
import com.arash.edu.bujournal.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentImportService {

    private final StudentRepository studentRepository;

    public List<Student> importManyFromCsv(String rawCsv, UUID groupId) {
        List<Student> students = parseRawCsv(rawCsv);
        students.forEach(s -> {
            s.setId(UUID.randomUUID());
            s.setGroupId(groupId);
        });

        return studentRepository.saveAll(students);
    }

    private List<Student> parseRawCsv(String rawCsv) {
        return Stream.of(rawCsv.split("[\n;]"))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(this::parseRawCsvLine)
                .collect(Collectors.toList());
    }

    private Student parseRawCsvLine(String rawCsvLine) {
        String[] elems = rawCsvLine.split(",");
        if (elems.length != 3) {
            throw new IllegalStateException("Expected 3 elements of raw csv line, received " + elems.length);
        }
        String lastName = elems[0];
        String firstName = elems[1];
        String patronymic = elems[2];
        return new Student(firstName, lastName, patronymic);
    }
}
