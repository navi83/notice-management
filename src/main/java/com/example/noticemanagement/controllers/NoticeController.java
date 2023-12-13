package com.example.noticemanagement.controllers;

import com.example.noticemanagement.entities.Notice;
import com.example.noticemanagement.services.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/notices")
@RestController
@Slf4j
@Tag(name = "Notice Controller")
public class NoticeController {

    public static final Logger LOGGER = LoggerFactory.getLogger(NoticeController.class);

    @Autowired
    private NoticeService noticeService;

    @Operation(description = "Get all notices")
    @GetMapping(value = "/all")
    public ResponseEntity<?> getAllNotices(@RequestParam(defaultValue = "0") Integer offset,
                                           @RequestParam(defaultValue = "100") Integer limit) {
        LOGGER.info("Process get all notices");
        Pageable pageable = PageRequest.of(offset, limit, Sort.by("id").descending());

        return ResponseEntity.ok(noticeService.getAllNotice(pageable));
    }

    @Operation(description = "Get notice by Id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getNoticeById(@PathVariable UUID id) {
        LOGGER.info("Process get notice by ID");
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }

    @Operation(description = "Create notice")
    @PostMapping(value = "/create-notice")
    public ResponseEntity createNotice(@RequestBody Notice notice) {
        LOGGER.info("Process create notice");
        return ResponseEntity.ok(noticeService.createNotice(notice));
    }

    @Operation(description = "Update notice")
    @PostMapping(value = "/update-notice")
    public ResponseEntity updateNotice(@RequestBody Notice notice) {
        LOGGER.info("Process update notice");
        return ResponseEntity.ok(noticeService.updateNotice(notice));
    }

    @Operation(description = "Delete notice")
    @PostMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteNoticeById(@PathVariable UUID id) {
        LOGGER.info("Process delete notice by ID");
        noticeService.deleteNoticeById(id);
        return ResponseEntity.ok("Notice deleted");
    }

}
