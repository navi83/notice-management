package com.example.noticemanagement.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentResponse {

    private UUID id;

    private String fileName;

    private String fileType;
}
