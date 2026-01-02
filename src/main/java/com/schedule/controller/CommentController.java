package com.schedule.controller;

import com.schedule.dto.*;
import com.schedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 쓰기(작성하기, 생성)
    @PostMapping("schedules/{scheduleId}/comments")
    public ResponseEntity<CommentCreateResponse> create(
            @PathVariable Long scheduleId, // 특정 번호(게시글) 가져오기
            @RequestBody CommentCreateRequest request
    ){
        CommentCreateResponse result = commentService.save(scheduleId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
