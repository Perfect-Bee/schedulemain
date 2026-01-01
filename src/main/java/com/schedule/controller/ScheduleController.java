package com.schedule.controller;

import com.schedule.dto.ScheduleCreateRequest;
import com.schedule.dto.ScheduleCreateResponse;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    // 1. ScheduleServie의 scheduleService 사용하고 싶다.
    // 서비스에 보내고싶음 : ScheduleService 만들기
    private final ScheduleService scheduleService;

    // 3. 일정 생성 : HTTP 응답 설계 -> dto 추가하기 요청/응답
    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreateResponse> create(
            @RequestBody ScheduleCreateRequest request // 4-1. 요청
    ){
        // 4-2. 응답 : 서비스 호출
        ScheduleCreateResponse result = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
