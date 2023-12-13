package com.example.noticemanagement.services.impls;

import com.example.noticemanagement.dtos.responses.AttachmentResponse;
import com.example.noticemanagement.entities.Attachment;
import com.example.noticemanagement.entities.Notice;
import com.example.noticemanagement.mappers.AttachmentMapper;
import com.example.noticemanagement.repositories.AttachmentRepository;
import com.example.noticemanagement.repositories.NoticeRepository;
import com.example.noticemanagement.services.AttachmentService;
import com.example.noticemanagement.services.NoticeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Transactional
@Service
public class AttachmentServiceImpl implements AttachmentService {

    public static final Logger LOGGER = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private AttachmentMapper attachmentResponseMapper;

    @Autowired
    private NoticeRepository noticeRepository;

    @Override
    public List<AttachmentResponse> createAttachment(UUID noticeId, List<MultipartFile> attachments) throws IOException {
        Notice notice = noticeRepository.findById(noticeId).get();
        List<AttachmentResponse> result = new ArrayList<>();
        for (MultipartFile item : attachments) {
            Attachment attachment = new Attachment();
            attachment.setFileName(item.getOriginalFilename());
            attachment.setFileType(item.getContentType());
            attachment.setFileContent(item.getBytes());
            attachment.setNotice(notice);
            result.add(attachmentResponseMapper.convertToDto(attachmentRepository.save(attachment)));
        }
        return result;
    }

    @Override
    public List<AttachmentResponse> getAttachment(UUID noticeId) {
        List<AttachmentResponse> attachmentResponses = new ArrayList<>();
        List<Attachment> attachments = attachmentRepository.findByNoticeId(noticeId);
        for (Attachment item : attachments) {
            attachmentResponses.add(attachmentResponseMapper.convertToDto(item));
        }
        return attachmentResponses;
    }

    @Override
    public void deleteAttachment(UUID attachmentId) {
        attachmentRepository.deleteById(attachmentId);
    }
}
