package com.arash.edu.bujournal.domain;

import com.arash.edu.bujournal.util.DateFormatUtil;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Table(schema = "bu", name = "bu_lesson")
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Long subjectId;

    public String getFormattedDate() {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        return DateFormatUtil.ddMM(date);
    }
}
