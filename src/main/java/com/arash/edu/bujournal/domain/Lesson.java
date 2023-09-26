package com.arash.edu.bujournal.domain;

import com.arash.edu.bujournal.domain.enums.LessonType;
import com.arash.edu.bujournal.util.DateFormatUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bu_lesson")
public class Lesson implements Serializable {

    @Id
    private UUID id;

    private String name;

    private LessonType type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private UUID subjectId;

    public String getFormattedDate() {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        return DateFormatUtil.ddMM(date);
    }
}
