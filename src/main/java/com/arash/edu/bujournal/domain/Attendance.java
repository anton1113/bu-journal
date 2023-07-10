package com.arash.edu.bujournal.domain;

import com.arash.edu.bujournal.constance.AttendanceConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@NoArgsConstructor
@Data
@Document(collection = "bu_attendance")
public class Attendance {

    @Id
    private UUID id;

    private UUID studentId;

    private UUID lessonId;

    private Integer mark;

    private Boolean isAbsent;

    public Attendance(UUID studentId, UUID lessonId) {
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
