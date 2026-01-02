package com.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content; // 댓글 내용
    private String author; // 작성자
    private String password; // 비밀번호
    // 추가: 어떤 일정에 달린 댓글인지
    private Long scheduleId;

    public Comment(Long scheduleId, String content, String author, String password) {
        this.scheduleId = scheduleId;
        this.content = content; // 댓글 내용
        this.author = author;   // 작성자
        this.password = password;   // 비밀번호
    }
}
