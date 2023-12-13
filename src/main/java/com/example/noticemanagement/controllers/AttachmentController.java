package com.example.noticemanagement.controllers;

import com.example.noticemanagement.services.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/attachments")
@RestController
@Slf4j
@Tag(name = "Attachment Controller")
public class AttachmentController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AttachmentController.class);

    @Autowired
    private AttachmentService attachmentService;

    @Operation(summary = "Get Attachment by noticeId")
    @GetMapping(value = "/{noticeId}")
    public ResponseEntity<?> getAttachment(@PathVariable UUID noticeId) {
        LOGGER.info("Process get attachment by noticeId");
        return ResponseEntity.ok(attachmentService.getAttachment(noticeId));
    }

    @Operation(summary = "Create Attachment")
    @PostMapping(value = "/{noticeId}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> createAttachment(@PathVariable UUID noticeId, @RequestBody List<MultipartFile> attachments) throws IOException {
        LOGGER.info("Process create attachment");
        return ResponseEntity.ok(attachmentService.createAttachment(noticeId, attachments));
    }

    @Operation(summary = "Delete Attachment")
    @PostMapping(value = "/delete/{attachmentId}")
    public ResponseEntity<?> deleteAttachment(@PathVariable UUID attachmentId) {
        LOGGER.info("Process delete attachment");
        attachmentService.deleteAttachment(attachmentId);
        return ResponseEntity.ok("Attachment deleted");
    }
}
