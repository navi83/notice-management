package com.example.noticemanagement.repositories;

import com.example.noticemanagement.entities.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface NoticeRepository extends JpaRepository<Notice, UUID> {

    @Modifying
    @Query("UPDATE Notice n " +
            "      SET n.title = :#{#notice.title}" +
            "      ,n.content = :#{#notice.content} " +
            "      ,n.startDate = :#{#notice.startDate} " +
            "      ,n.endDate = :#{#notice.endDate} " +
            " WHERE n.id = :#{#notice.id}")
    int updateNotice(Notice notice);

    @Modifying
    @Query("UPDATE Notice n " +
            "      SET n.totalView = :totalView" +
            "      WHERE n.id = :id")
    int updateTotalView(Integer totalView, UUID id);
}
