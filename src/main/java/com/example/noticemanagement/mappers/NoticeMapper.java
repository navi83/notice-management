package com.example.noticemanagement.mappers;

import com.example.noticemanagement.dtos.requests.NoticeRequest;
import com.example.noticemanagement.dtos.responses.NoticeResponse;
import com.example.noticemanagement.entities.Notice;
import org.springframework.stereotype.Component;

@Component
public class NoticeMapper {

    public NoticeResponse convertToDto(Notice notice) {
        NoticeResponse noticeResponse = new NoticeResponse();
        noticeResponse.setId(notice.getId());
        noticeResponse.setContent(notice.getContent());
        noticeResponse.setStartDate(notice.getStartDate());
        noticeResponse.setTitle(notice.getTitle());
        noticeResponse.setTotalView(notice.getTotalView());
        if (notice.getAuthor() != null) {
            noticeResponse.setAuthor(notice.getAuthor().getUsername());
        }
        return noticeResponse;
    }

    public Notice convertToEntity(NoticeRequest noticeRequest) {
        Notice notice = new Notice();
        notice.setTitle(noticeRequest.getTitle());
        notice.setContent(noticeRequest.getContent());
        notice.setStartDate(noticeRequest.getStartDate());
        notice.setEndDate(noticeRequest.getEndDate());
        return notice;
    }
}
