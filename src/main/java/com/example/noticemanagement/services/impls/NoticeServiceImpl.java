package com.example.noticemanagement.services.impls;

import com.example.noticemanagement.dtos.responses.NoticeResponse;
import com.example.noticemanagement.entities.Notice;
import com.example.noticemanagement.mappers.NoticeMapper;
import com.example.noticemanagement.repositories.AttachmentRepository;
import com.example.noticemanagement.repositories.NoticeRepository;
import com.example.noticemanagement.security.entity.User;
import com.example.noticemanagement.security.repository.UserRepository;
import com.example.noticemanagement.services.NoticeService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Transactional
@Service
public class NoticeServiceImpl implements NoticeService {

    public static final Logger LOGGER = LoggerFactory.getLogger(NoticeServiceImpl.class);

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<NoticeResponse> getAllNotice(Pageable pageable) {
        Page<NoticeResponse> noticeResponsePage = noticeRepository.findAll(pageable).map(notice -> {
            NoticeResponse noticeResponse = noticeMapper.convertToDto(notice);
            return noticeResponse;
        });
        return noticeResponsePage;
    }

    @Override
    public NoticeResponse getNoticeById(UUID id) {
        Notice notice = noticeRepository.findById(id).orElseThrow();
        NoticeResponse noticeResponse = noticeMapper.convertToDto(notice);
        return noticeResponse;
    }

    @Override
    public NoticeResponse createNotice(Notice notice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElseThrow();
            notice.setAuthor(user);
        }
        notice.setTotalView(0);
        Notice createdNotice = noticeRepository.save(notice);
        NoticeResponse noticeResponse = noticeMapper.convertToDto(createdNotice);
        return noticeResponse;
    }

    @Override
    public NoticeResponse updateNotice(Notice notice) {
        Notice updatedNotice = noticeRepository.save(notice);
        NoticeResponse noticeResponse = noticeMapper.convertToDto(updatedNotice);
        return noticeResponse;
    }

    @Override
    public void deleteNoticeById(UUID id) {
        noticeRepository.deleteById(id);
    }
}
