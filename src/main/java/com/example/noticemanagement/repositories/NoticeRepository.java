package com.example.noticemanagement.repositories;

import com.example.noticemanagement.entities.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface NoticeRepository extends JpaRepository<Notice, UUID> {
}
