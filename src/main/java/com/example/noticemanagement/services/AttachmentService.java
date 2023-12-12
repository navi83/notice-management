package com.example.noticemanagement.services;

import com.example.noticemanagement.dtos.responses.AttachmentResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface AttachmentService {

    List<AttachmentResponse> createAttachment(UUID noticeId, List<MultipartFile> attachment) throws IOException;

    List<AttachmentResponse> getAttachment(UUID noticeId);

    void deleteAttachment(UUID attachmentId);

}
