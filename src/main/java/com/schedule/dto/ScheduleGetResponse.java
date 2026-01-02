package com.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ScheduleGetResponse {
    private final Long id;
    private final String title; // 일정 제목
    private final String content; // 일정 내용
    private final String author; // 작성자
    // 비밀번호 응답 제외
    // 생성/수정일
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    // 댓글 리스트 추가
    private final List<CommentGetResponse> comments;

    public ScheduleGetResponse(
            Long id, String title, String content, String author,
            LocalDateTime createdAt, LocalDateTime modifiedAt,
            List<CommentGetResponse> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.comments = comments;
    }
}
