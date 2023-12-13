package com.example.noticemanagement;

import com.example.noticemanagement.dtos.responses.AttachmentResponse;
import com.example.noticemanagement.dtos.responses.NoticeResponse;
import com.example.noticemanagement.entities.Attachment;
import com.example.noticemanagement.entities.Notice;
import com.example.noticemanagement.mappers.AttachmentMapper;
import com.example.noticemanagement.repositories.AttachmentRepository;
import com.example.noticemanagement.repositories.NoticeRepository;
import com.example.noticemanagement.services.impls.AttachmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AttachmentServiceTest {

    @Mock
    private AttachmentRepository attachmentRepository;

    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private AttachmentServiceImpl attachmentService;

    @Mock
    private AttachmentMapper attachmentMapper;

    public AttachmentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAttachmentSuccess() throws IOException {
        // Create data
        UUID noticeId = UUID.randomUUID();
        List<MultipartFile> mockFiles = createMockAttachments();

        List<Attachment> createdAttachments = createMockAttachmentEntities();
        List<AttachmentResponse> expectedResponses = createMockAttachmentResponses();

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(createMockNoticeEntity()));
        when(attachmentRepository.save(any())).thenReturn(createdAttachments.get(1));
        when(attachmentMapper.convertToDto(any(Attachment.class))).thenAnswer(invocation -> {
            Attachment attachment = invocation.getArgument(0);
            return createMockAttachmentResponse(attachment);
        });
        // Execute
        List<AttachmentResponse> resultResponses = attachmentService.createAttachment(noticeId, mockFiles);

        // Assert
        Assertions.assertNotNull(resultResponses);
        Assertions.assertEquals(expectedResponses.size(), resultResponses.size());

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(attachmentRepository, times(createdAttachments.size())).save(any());
        verify(attachmentMapper, times(createdAttachments.size())).convertToDto(createMockAttachmentEntity());
    }

    @Test
    void testCreateAttachmentFail() {
        // Create data
        UUID noticeId = UUID.randomUUID();
        List<MultipartFile> mockFiles = createMockAttachments();

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(createMockNoticeEntity()));
        when(attachmentRepository.save(any())).thenThrow(new RuntimeException("Error when create attachment"));

        // Execute
        Assertions.assertThrows(RuntimeException.class, () -> {
            attachmentService.createAttachment(noticeId, mockFiles);
        });

        verify(noticeRepository, times(1)).findById(noticeId);
        verify(attachmentRepository, times(1)).save(any());
        verifyNoMoreInteractions(attachmentMapper);
    }

    private List<MultipartFile> createMockAttachments() {
        return List.of(createMockMultipartFile("file1"), createMockMultipartFile("file2"));
    }

    private MultipartFile createMockMultipartFile(String fileName) {
        return mock(MultipartFile.class, withSettings().name(fileName));
    }

    private Attachment createMockAttachmentEntity() {
        Attachment attachment = new Attachment();
        attachment.setId(UUID.randomUUID());
        attachment.setFileName("mockFile.txt");
        attachment.setNotice(createMockNoticeEntity());
        return attachment;
    }

    private List<Attachment> createMockAttachmentEntities() {
        return List.of(createMockAttachmentEntity(), createMockAttachmentEntity());
    }

    private AttachmentResponse createMockAttachmentResponse(Attachment attachment) {
        AttachmentResponse response = new AttachmentResponse();
        response.setId(attachment.getId());
        response.setFileName(attachment.getFileName());
        return response;
    }

    private List<AttachmentResponse> createMockAttachmentResponses() {
        return List.of(createMockAttachmentResponse(createMockAttachmentEntity()),
                createMockAttachmentResponse(createMockAttachmentEntity()));
    }

    private Notice createMockNoticeEntity() {
        Notice notice = new Notice();
        notice.setId(UUID.randomUUID());
        notice.setTitle("Test Title");
        notice.setContent("Test Content");
        notice.setTotalView(0);
        return notice;
    }

}
