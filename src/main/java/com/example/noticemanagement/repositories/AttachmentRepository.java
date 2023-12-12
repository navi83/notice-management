package com.example.noticemanagement.repositories;

import com.example.noticemanagement.entities.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {
    List<Attachment> findByNoticeId(UUID noticeId);
}
