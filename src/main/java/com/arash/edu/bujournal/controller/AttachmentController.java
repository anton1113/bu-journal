package com.arash.edu.bujournal.controller;

import com.arash.edu.bujournal.domain.Attachment;
import com.arash.edu.bujournal.service.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class AttachmentController {

    private final AttachmentService attachmentService;

    @GetMapping(path = "/attachments/{attachmentId}/download")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable UUID attachmentId) {
        Attachment attachment = attachmentService.findById(attachmentId);
        ByteArrayResource resource = new ByteArrayResource(attachment.getContent());

        return ResponseEntity.ok().headers(this.headers(attachment.getName()))
                .contentLength(attachment.getContent().length)
                .contentType(MediaType.parseMediaType
                        ("application/octet-stream")).body(resource);
    }

    private HttpHeaders headers(String name) {
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")
                .filename(name, StandardCharsets.UTF_8)
                .build();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(contentDisposition);
        headers.add("Cache-Control", "no-cache, no-store,"
                + " must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return headers;
    }
}
