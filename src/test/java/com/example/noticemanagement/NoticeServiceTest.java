package com.example.noticemanagement;

import com.example.noticemanagement.dtos.responses.NoticeResponse;
import com.example.noticemanagement.entities.Notice;
import com.example.noticemanagement.mappers.NoticeMapper;
import com.example.noticemanagement.repositories.NoticeRepository;
import com.example.noticemanagement.services.impls.NoticeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class NoticeServiceTest {

    @Mock
    private NoticeRepository noticeRepository;

    @InjectMocks
    private NoticeServiceImpl noticeService;

    @Mock
    private NoticeMapper noticeMapper;

    public NoticeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateNoticeSuccess() {
        // Create data
        Notice inputNotice = validNotice();
        Notice savedNotice = savedNotice();
        NoticeResponse noticeResponse = noticeResponse();

        when(noticeRepository.save(any(Notice.class))).thenReturn(savedNotice);
        when(noticeMapper.convertToDto(any(Notice.class))).thenReturn(noticeResponse);

        // Execute
        NoticeResponse resultNotice = noticeService.createNotice(inputNotice);

        // Assert
        verify(noticeRepository, times(1)).save(any(Notice.class));
        Assertions.assertNotNull(resultNotice.getId());
        Assertions.assertEquals("Test Title", resultNotice.getTitle());
        Assertions.assertEquals("Test Content", resultNotice.getContent());
    }

    @Test
    void testCreateNoticeFail() {
        // Create invalid notice, missing title
        Notice inputNotice = new Notice();
        inputNotice.setContent("Test Content");

        // Execute
        noticeService.createNotice(inputNotice);

        // Assert
        verify(noticeMapper, times(0)).convertToDto(any(Notice.class));

    }

    @Test
    void testGetNoticeByIdSuccess() {
        // Create data
        UUID noticeId = UUID.randomUUID();
        Notice savedNotice = savedNotice();
        NoticeResponse noticeResponse = noticeResponse();

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.of(savedNotice));
        when(noticeMapper.convertToDto(savedNotice)).thenReturn(noticeResponse);

        // Execute
        NoticeResponse resultDTO = noticeService.getNoticeById(noticeId);

        // Assert
        Assertions.assertNotNull(resultDTO);
        Assertions.assertEquals(noticeResponse, resultDTO);
        verify(noticeRepository, times(1)).findById(noticeId);
        verify(noticeMapper, times(1)).convertToDto(savedNotice);
    }

    @Test
    void testGetNoticeByIdNotFound() {
        // Create data
        UUID noticeId = UUID.randomUUID();

        when(noticeRepository.findById(noticeId)).thenReturn(Optional.empty());

        // Execute
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            noticeService.getNoticeById(noticeId);
        });

        // Assert
        verify(noticeRepository, times(1)).findById(noticeId);
        verifyNoInteractions(noticeMapper);
    }

    private Notice validNotice() {
        Notice inputNotice = new Notice();
        inputNotice.setTitle("Test Title");
        inputNotice.setContent("Test Content");
        return inputNotice;
    }

    private NoticeResponse noticeResponse() {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.setId(UUID.randomUUID());
        noticeResponse.setTitle("Test Title");
        noticeResponse.setContent("Test Content");
        return noticeResponse;
    }

    private Notice savedNotice() {
        Notice savedNotice = new Notice();
        savedNotice.setId(UUID.randomUUID());
        savedNotice.setTitle("Test Title");
        savedNotice.setContent("Test Content");
        savedNotice.setTotalView(0);
        return savedNotice;
    }
}
