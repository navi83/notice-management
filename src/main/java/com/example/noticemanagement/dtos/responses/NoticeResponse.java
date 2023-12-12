package com.example.noticemanagement.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeResponse {

    private UUID id;

    private String title;

    private String content;

    private LocalDateTime startDate;

    private Integer totalView;

    private String author;

}
