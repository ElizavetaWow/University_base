package com.krylova.demo2.controller;

import com.krylova.demo2.exception.ResourceNotFoundException;
import com.krylova.demo2.model.Schedule;
import com.krylova.demo2.repository.GroupRepository;
import com.krylova.demo2.repository.TestListRepository;
import com.krylova.demo2.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private TestListRepository testListRepository;

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/schedules")
    public Page<Schedule> getAllSchedules(Pageable pageable) {
        return scheduleRepository.findAll(pageable);
    }

    @GetMapping("/testLists/{testListId}/schedules")
    public List<Schedule> getSchedulesByTestListId(Long testListId) {
        return scheduleRepository.findByTestListId(testListId);
    }

    @GetMapping("/groups/{groupId}/schedules")
    public List<Schedule> getSchedulesByGroupId(Long groupId) {
        return scheduleRepository.findByGroupId(groupId);
    }

    @PostMapping("/schedules")
    public Schedule createSchedule(@Valid @RequestBody Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @PostMapping("/testLists/{testListId}/schedules")
    public Schedule addScheduleToTestList(@PathVariable Long testListId, @Valid @RequestBody Schedule schedule) {
        return testListRepository.findById(testListId)
                .map(testList -> {
                    schedule.setTestList(testList);
                    return scheduleRepository.save(schedule);
                }).orElseThrow(() -> new ResourceNotFoundException("TestList not found with Id " + testListId));
    }

    @PostMapping("/groups/{groupId}/schedules")
    public Schedule addScheduleToGroup(@PathVariable Long groupId, @Valid @RequestBody Schedule schedule) {
        return groupRepository.findById(groupId)
                .map(group -> {
                    schedule.setGroup(group);
                    return scheduleRepository.save(schedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Group not found with Id " + groupId));
    }

    @PutMapping("/schedules/{scheduleId}")
    public Schedule updateSchedule(@PathVariable Long scheduleId,
                           @Valid @RequestBody Schedule scheduleRequest) {
        return scheduleRepository.findById(scheduleId)
                .map(schedule -> {
                    schedule.setActive(scheduleRequest.isActive());
                    schedule.setDuration(scheduleRequest.getDuration());
                    schedule.setEnd_dt(scheduleRequest.getEnd_dt());
                    schedule.setStart_dt(scheduleRequest.getStart_dt());
                    schedule.setStart_time(scheduleRequest.getStart_time());
                    schedule.setEnd_time(scheduleRequest.getEnd_time());
                    schedule.setTestList(scheduleRequest.getTestList());
                    schedule.setGroup(scheduleRequest.getGroup());
                    return scheduleRepository.save(schedule);
                }).orElseThrow(() -> new ResourceNotFoundException("Schedule not found with Id " + scheduleId));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<?> deleteScheduleByTestListId(@PathVariable Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .map(schedule -> {
                    scheduleRepository.delete(schedule);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Schedule not found with Id " + scheduleId));
    }
}
