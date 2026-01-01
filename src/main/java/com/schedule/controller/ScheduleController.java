package com.schedule.controller;

import com.schedule.dto.*;
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

    // 1. 선택한 일정 수정(원하는 걸 선택해서 수정해야하니 2. 요청/응답)
    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResponse> update(
            @PathVariable Long scheduleId,
            // 수정사항 : HTTP 요청을 자바 본문(body)으로 바꿔줌(일단 그냥 그렇다고 알고있자)
            // 여기에서 입력 들어옴 : 제목, 작성자, *비밀번호*
            @RequestBody ScheduleUpdateRequest request
    ){                                                      // 4. update(schedule에 생성자 필요함)
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.update(scheduleId, request));
    }

}
