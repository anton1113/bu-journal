package com.arash.edu.bujournal.service;

import com.arash.edu.bujournal.domain.Attachment;
import com.arash.edu.bujournal.domain.Source;
import com.arash.edu.bujournal.error.NotFoundException;
import com.arash.edu.bujournal.repository.SourceRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SourceService {

    private final SourceRepository sourceRepository;
    private final AttachmentService attachmentService;

    public Source findById(@NonNull UUID id) {
        log.info("Find source by id [{}]", id);
        return sourceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Source not found by id " + id));
    }

    public List<Source> findAllByLessonId(@NonNull UUID lessonId) {
        log.info("Find all sources by lessonId {}", lessonId);
        return sourceRepository.findAllByLessonId(lessonId);
    }

    public Source addSource(@NonNull Source source, @NonNull UUID lessonId) {
        log.info("Adding source {}", source);

        if (source.getId() == null) {
            source.setId(randomUUID());
        }
        source.setLessonId(lessonId);

        if (source.getFile() != null) {
            Attachment attachment = attachmentService.addAttachment(source.getFile(), source.getId());
            source.setAttachmentId(attachment.getId());
            source.setAttachmentName(attachment.getName());
        }

        return sourceRepository.save(source);
    }

    public Source editSource(@NonNull UUID id, @NonNull Source source) {
        log.info("Editing source with {}, {}", id, source);
        if (!sourceRepository.existsById(id)) {
            throw new NotFoundException("Source with id " + id + "not found, unable to edit");
        }
        source.setId(id);
        return sourceRepository.save(source);
    }

    public void deleteSource(@NonNull UUID id) {
        log.info("Delete source by id [{}]", id);
        sourceRepository.deleteById(id);
    }
}
