package com.schedule.dto;

import lombok.Getter;

// 4-1. 요청(만들어달라 요구하는거니 id 필요 없음)
@Getter
public class ScheduleCreateRequest {
    private String title; // 일정 제목
    private String content; // 일정 내용
    private String author; // 작성자
    private String password; // 비밀번호(API반환 시 주의)
}
