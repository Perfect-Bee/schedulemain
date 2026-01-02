package com.schedule.service;

import com.schedule.dto.CommentCreateRequest;
import com.schedule.dto.CommentCreateResponse;
import com.schedule.entity.Comment;
import com.schedule.entity.Schedule;
import com.schedule.repository.CommentRepository;
import com.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository; // Schedule 조회용

    @Transactional
    //                                게시글 번호
    public CommentCreateResponse save(Long scheduleId, CommentCreateRequest request) {
        // 게시글 있는지 확인
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("schedule 없음.")
        );

        // 댓글 개수 제한
        long count = commentRepository.countByScheduleId(scheduleId);
        if (count >= 10) {
            throw new IllegalStateException("하나의 일정에는 댓글을 10개까지만 작성할 수 있습니다.");
        }

        Comment comment = new Comment(
                scheduleId,
                request.getContent(),
                request.getAuthor(),
                request.getPassword()
        );
        Comment savedComment = commentRepository.save(comment);

        return new CommentCreateResponse(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getAuthor(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }

    // 댓글 생성

}
