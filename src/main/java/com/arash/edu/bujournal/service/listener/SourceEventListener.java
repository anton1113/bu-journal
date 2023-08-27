package com.arash.edu.bujournal.service.listener;

import com.arash.edu.bujournal.domain.Source;
import com.arash.edu.bujournal.service.AttachmentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class SourceEventListener {

    private final AttachmentService attachmentService;

    public void onSourceDeleted(@NonNull Source source) {
        log.info("Received source_deleted event, sourceId={}", source.getId());

        if (source.getAttachmentId() != null) {
            attachmentService.deleteAttachment(source.getAttachmentId());
        }
    }
}
