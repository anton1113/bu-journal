package com.arash.edu.bujournal.domain.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SourceView {

    private String name;

    private String link;

    private MultipartFile file;
}
