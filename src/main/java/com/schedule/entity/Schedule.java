package com.schedule.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "Schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
                    // BaseEntity 클래스 생성함
public class Schedule extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 이건 기본적으로 제공함

    private String title; // 일정 제목
    private String content; // 일정 내용
    private String author; // 작성자
    private String password; // 비밀번호(API반환 시 주의)

    // 생성자
    public Schedule(String title, String content, String author, String password) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.password = password;
    }

    // 일정 수정 : PutMapping 미리 작성
    // 주의! : 제목과 작성자 명만 변경 가능함.
    public void update(String title, String author) {
        this.title = title;
        this.author = author;
    }

}
