package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Attachment;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.AttachmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;

    public Attachment findByNullableId(UUID id) {
        log.info("Get attachment by nullable id [{}]", id);
        if (id == null) {
            return null;
        }
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attachment not found by id " + id));
    }
}
