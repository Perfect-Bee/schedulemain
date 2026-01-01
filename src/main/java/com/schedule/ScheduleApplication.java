package com.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing Entity 생성/수정시간 체크하는 기능
// sava()또는 update() 사용 시 JPA Entity 작동 -> createdate나 modifieddate 자동 세팅
@EnableJpaAuditing
@SpringBootApplication
public class ScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleApplication.class, args);
    }

}
