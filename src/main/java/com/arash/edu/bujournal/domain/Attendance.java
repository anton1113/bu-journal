package com.arash.edu.bujournal.domain;

import com.arash.edu.bujournal.constance.AttendanceConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Table(schema = "bu", name = "bu_attendance")
@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    @Column(nullable = false)
    private Long lessonId;

    private Integer mark;

    private Boolean isAbsent;

    public Attendance(Long studentId, Long lessonId) {
        this.studentId = studentId;
        this.lessonId = lessonId;
    }

    public String toCellValue() {
        if (Boolean.TRUE.equals(isAbsent)) {
            return AttendanceConstants.ABSENT_CODE;
        }
        if (mark == null) {
            return StringUtils.EMPTY;
        }
        return String.valueOf(mark);
    }
}
