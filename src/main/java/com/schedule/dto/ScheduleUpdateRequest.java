package com.schedule.dto;

import lombok.Getter;

// 3-1. 요청(만들어달라 요구하는거니 id 필요 없음)
// 수정 : 제목과 작성자만 + 요청 시 비밀번호 함께 제공
@Getter
public class ScheduleUpdateRequest {
    private String title; // 일정 제목
    private String author; // 작성자
    private String password; // 비밀번호(API반환 시 주의)
}
