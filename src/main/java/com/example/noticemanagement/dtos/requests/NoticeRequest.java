package com.example.noticemanagement.dtos.requests;

import com.example.noticemanagement.security.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeRequest {

    private String title;

    private String content;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
