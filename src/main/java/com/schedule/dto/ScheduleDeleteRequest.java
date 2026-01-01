package com.schedule.dto;

import lombok.Getter;

// 삭제 시 확인할 비밀번호
@Getter
public class ScheduleDeleteRequest {
    private String password;
}
