package com.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

// 3-2. 응답
@Getter
public class ScheduleUpdateResponse {
    private final Long id;
    private final String title; // 일정 제목
    private final String content; // 일정 내용
    private final String author; // 작성자
    // 비밀번호 응답 제외
    // 생성/수정일
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public ScheduleUpdateResponse(Long id, String title, String content, String author, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;

        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
