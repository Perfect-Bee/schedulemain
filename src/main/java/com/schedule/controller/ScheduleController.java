package com.schedule.controller;

import com.schedule.dto.ScheduleCreateRequest;
import com.schedule.dto.ScheduleCreateResponse;
import com.schedule.dto.ScheduleGetResponse;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleCreateResponse> create(
            @RequestBody ScheduleCreateRequest request // 4-1. 요청
    ){
        ScheduleCreateResponse result = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 조회
    // 1. 전체 조회 : 전체 다 보기만 하면 됨 = List로
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getAll(){
                                                        // 3. 전부 찾는 것을 원함 : findAll 만들어
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }

}
