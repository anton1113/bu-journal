package com.arash.edu.bujournal.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "bu_source")
public class Source implements Serializable {

    @Id
    private UUID id;

    private UUID lessonId;

    private String name;

    private String link;

    private UUID attachmentId;

    private String attachmentName;

    @Transient
    private MultipartFile file;
}
