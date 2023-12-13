package com.example.noticemanagement.services;

import com.example.noticemanagement.dtos.requests.NoticeRequest;
import com.example.noticemanagement.dtos.responses.NoticeResponse;
import com.example.noticemanagement.entities.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface NoticeService {

    Page<NoticeResponse> getAllNotice(Pageable pageable);

    NoticeResponse getNoticeById(UUID id);

    NoticeResponse createNotice(NoticeRequest notice);

    NoticeResponse updateNotice(NoticeRequest notice, UUID id);

    void deleteNoticeById(UUID id);

}
