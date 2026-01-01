package com.schedule.controller;

import com.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final ScheduleService scheduleService;

    // 댓글 쓰기
}
