package com.arash.edu.bujournal.domain;

import com.arash.edu.bujournal.util.DateFormatUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Document(collection = "bu_lesson")
public class Lesson {

    @Id
    private UUID id;

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
