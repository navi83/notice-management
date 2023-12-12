package com.example.noticemanagement.mappers;

import com.example.noticemanagement.dtos.responses.AttachmentResponse;
import com.example.noticemanagement.entities.Attachment;
import org.springframework.stereotype.Component;

@Component
public class AttachmentMapper {

    public AttachmentResponse convertToDto(Attachment attachment) {
        AttachmentResponse attachmentResponse = new AttachmentResponse();
        attachmentResponse.setId(attachment.getId());
        attachmentResponse.setFileName(attachment.getFileName());
        attachmentResponse.setFileType(attachment.getFileType());
        return attachmentResponse;
    }
}
