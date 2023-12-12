package com.example.noticemanagement.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ATTACHMENTS")
public class Attachment extends BaseEntity {

    @Column(name = "FILE_NAME", nullable = false)
    private String fileName;

    @Column(name = "FILE_TYPE", nullable = false)
    private String fileType;

    @Lob
    @Column(name = "FILE_CONTENT", nullable = false)
    private byte[] fileContent;

    @ManyToOne
    @JoinColumn(name = "NOTICE_ID")
    private Notice notice;

}
