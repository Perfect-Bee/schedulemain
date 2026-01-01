package com.schedule.service;

import com.schedule.dto.ScheduleCreateRequest;
import com.schedule.dto.ScheduleCreateResponse;
import com.schedule.dto.ScheduleGetResponse;
import com.schedule.entity.Schedule;
import com.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

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
        // 내가 만들려고 하는 구조는 List<ScheduleGetResponse>
        // Spring 제공 매서드 : findAll()은 그냥 조회. findAllByOrderByModifiedAtDesc()은 findAll + modified 내림차순
        List<Schedule> schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        // List<ScheduleGetResponse> 구조로 dtos 변수 만들고
        List<ScheduleGetResponse> dtos =  new ArrayList<>();
        // finaAll으로 찾은 것을 dto에 넣어서
        for  (Schedule schedule : schedules) {
            ScheduleGetResponse dto = new ScheduleGetResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getAuthor(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            // dto -> dtos에 넣기
            dtos.add(dto);
        }
        return dtos;
    }
}
