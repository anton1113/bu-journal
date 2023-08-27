package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Attachment;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.AttachmentRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    @SneakyThrows
    public Attachment addAttachment(@NonNull MultipartFile multipartFile, @NonNull UUID externalId) {
        log.info("Add attachment {} to {}", multipartFile.getName(), externalId);
        Attachment attachment = new Attachment();
        attachment.setId(randomUUID());
        attachment.setName(multipartFile.getOriginalFilename());
        attachment.setContent(multipartFile.getBytes());
        return attachmentRepository.save(attachment);
    }

    public Attachment findById(UUID id) {
        log.info("Get attachment by nullable id [{}]", id);
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attachment not found by id " + id));
    }

    public void deleteAttachment(@NonNull UUID attachmentId) {
        log.info("Delete attachment {}", attachmentId);
        attachmentRepository.deleteById(attachmentId);
    }
}
