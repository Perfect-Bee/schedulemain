package com.schedule.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class CommentCreateResponse {
    private final Long id;

    private final String content; // 일정 내용
    private final String author; // 작성자
    // 비밀번호 응답 제외
    // 생성/수정일
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public CommentCreateResponse(Long id, String content, String author, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.content = content;
        this.author = author;
    // 응답에는 비밀번호 제외
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
