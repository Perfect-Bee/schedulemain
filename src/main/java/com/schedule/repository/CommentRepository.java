package com.schedule.repository;

import com.schedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 일정별 댓글 수 카운트 : countBy까지는 된다는데, 앞에 이건 뭐야. 이걸로 된다고?
    long countByScheduleId(Long scheduleId);
}
