package com.schedule.repository;

import com.schedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 일정별 댓글 수 카운트 : countBy까지는 된다는데, 앞에 이건 뭐야. 이걸로 된다고?
    // 아닌데? 이건 그냥 변수 설정해서 개수 세는건데? 그정도는 Jpa갸 해주는건가?
    // ㅇㅇ 해주는거임. JPA개쩔어
    long countByScheduleId(Long scheduleId);

    // 일정별 댓글 조회
    // findBy : 찾기, OrderBy : (ScheduleId)조회 기준 열 CreateAt 방식으로 Asc : 오름차순
    List<Comment> findByScheduleIdOrderByCreatedAtAsc(Long scheduleId);
}
