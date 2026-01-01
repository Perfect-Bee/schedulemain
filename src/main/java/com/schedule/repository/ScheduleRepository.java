package com.schedule.repository;

import com.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    // findAll() 기능 + 수정일(Modified) 기준 내림차순(Desc)
    List<Schedule> findAllByOrderByModifiedAtDesc();
}
