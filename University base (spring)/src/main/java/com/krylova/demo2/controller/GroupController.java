package com.krylova.demo2.controller;

import com.krylova.demo2.exception.ResourceNotFoundException;
import com.krylova.demo2.model.Group;
import com.krylova.demo2.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @GetMapping("/groups")
    public Page<Group> getGroups(Pageable pageable) {
        return groupRepository.findAll(pageable);
    }

    @PostMapping("/groups")
    public Group createGroup(@Valid @RequestBody Group group) {
        return groupRepository.save(group);
    }

    @PutMapping("/groups/{groupId}")
    public Group updateGroup(@PathVariable Long groupId, @Valid @RequestBody Group groupRequest) {
        return groupRepository.findById(groupId)
                .map(group -> {
                    group.setSemester(groupRequest.getSemester());
                    group.setYear(groupRequest.getYear());
                    group.setName(groupRequest.getName());
                    return groupRepository.save(group);
                }).orElseThrow(() -> new ResourceNotFoundException("Group not found with Id " + groupId));
    }

    @DeleteMapping("/groups/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long groupId) {
        return groupRepository.findById(groupId)
                .map(group -> {
                    groupRepository.delete(group);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Group not found with Id " + groupId));
    }
}
