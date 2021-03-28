package com.krylova.demo2.repository;

import com.krylova.demo2.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByTestListId(Long testListId);
    List<Schedule> findByGroupId(Long groupId);
}
