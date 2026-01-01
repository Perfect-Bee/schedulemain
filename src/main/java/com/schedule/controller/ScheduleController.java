package com.schedule.controller;

import com.schedule.dto.ScheduleCreateRequest;
import com.schedule.dto.ScheduleCreateResponse;
import com.schedule.dto.ScheduleGetResponse;
import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // 전체 조회 : 전체 다 보기만 하면 됨 = List로
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleGetResponse>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findAll());
    }
    // 1. 선택 조회 : 특정한 것만 필요하기에 List 아님
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleGetResponse> getOne(
            //@PathVariable : 경로변수 표기
            @PathVariable Long scheduleId
    ){                                                          // 2. 하나만 찾는거 필요해. 서비스! 만들어!
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.findOne(scheduleId));
    }

}
