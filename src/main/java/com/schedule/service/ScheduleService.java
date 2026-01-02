package com.schedule.service;

import com.schedule.dto.*;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.schedule.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public ScheduleCreateResponse save(ScheduleCreateRequest request){
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getAuthor(),
                request.getPassword()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new  ScheduleCreateResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getAuthor(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 전체 조회에 필요함 + 읽기만 하면 됨
    @Transactional(readOnly = true)
    public List<ScheduleGetResponse> findAll() {// 4. 원하는대로 만듦
        List<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        List<ScheduleGetResponse> dtos =  new ArrayList<>();
        for  (Schedule schedule : schedules) {
            ScheduleGetResponse dto = new ScheduleGetResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt(),
                    null // 전체 조회는 확인 안 함
            );
            // dto -> dtos에 넣기
            dtos.add(dto);
        }
        return dtos;
    }
    // 선택 조회(읽기만 함)
    @Transactional(readOnly = true)
    public ScheduleGetResponse findOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("찾는 일정 없음")
        );
        // 댓글 조회
        List<CommentGetResponse> commentDtos = commentRepository.findByScheduleIdOrderByCreatedAtAsc(scheduleId)
                .stream()
                .map(c -> new CommentGetResponse(
                        c.getId(),
                        c.getContent(),
                        c.getAuthor(),
                        c.getCreatedAt(),
                        c.getModifiedAt()
                ))
                .toList();
        return new  ScheduleGetResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getAuthor(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt(),
                commentDtos
        );
    }

    // 수정하기(update)
    @Transactional
    public ScheduleUpdateResponse update(Long scheduleId, ScheduleUpdateRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("수정할 일정 없음")
        );
        
        // 비밀번호 검증 필요 : 객체비교 : s1.equals(s2) : schedule(DB값)와 요청값(입력한값) 비교
        // 같지 않으면(!) 예외처리 / 같으면 다음 단계로
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("비밀번호 불일치");
        }
        // 제목, 작성자만 수정함
        schedule.update(request.getTitle(), request.getAuthor());
        return  new  ScheduleUpdateResponse(
                schedule.getId(),
                schedule.getTitle(),    // 얘랑
                schedule.getContent(),  // 이거 수정 안 함(update 주의)
                schedule.getAuthor(),   // 얘만 수정
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 2. 선택한 일정 삭제
    @Transactional
    public void delete(Long scheduleId, ScheduleDeleteRequest request) {
        // 비밀번호 체크를 위해 exist 안씀
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("삭제할 일정 없음")
        );

        // 비밀번호 검증
        if (!schedule.getPassword().equals(request.getPassword())) {
            throw new IllegalStateException("비밀번호 불일치");
        }

        scheduleRepository.deleteById(scheduleId);
    }
}
