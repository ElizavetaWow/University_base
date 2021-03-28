package com.krylova.demo2.controller;

import com.krylova.demo2.exception.ResourceNotFoundException;
import com.krylova.demo2.model.TestList;
import com.krylova.demo2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class TestListController {

    @Autowired
    private TestListRepository testListRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping("/testLists")
    public Page<TestList> getAllTestLists(Pageable pageable) {
        return testListRepository.findAll(pageable);
    }

    @GetMapping("/users/{userId}/testLists")
    public List<TestList> getTestListsByUserId(Long userId) {
        return testListRepository.findByUserId(userId);
    }
    @GetMapping("/subjects/{subjectId}/testLists")
    public List<TestList> getTestListsBySubjectId(Long subjectId) {
        return testListRepository.findBySubjectId(subjectId);
    }

    @PostMapping("/testLists")
    public TestList createTestList(@Valid @RequestBody TestList testList) {
        return testListRepository.save(testList);
    }

    @PostMapping("/users/{userId}/testLists")
    public TestList addTestListToUser(@PathVariable Long userId, @Valid @RequestBody TestList testList) {
        return userRepository.findById(userId)
                .map(user -> {
                    testList.setUser(user);
                    return testListRepository.save(testList);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with Id " + userId));
    }

    @PostMapping("/subjects/{subjectId}/testLists")
    public TestList addTestListToSubject(@PathVariable Long subjectId, @Valid @RequestBody TestList testList) {
        return subjectRepository.findById(subjectId)
                .map(subject -> {
                    testList.setSubject(subject);
                    return testListRepository.save(testList);
                }).orElseThrow(() -> new ResourceNotFoundException("Subject not found with Id " + subjectId));
    }

    @PutMapping("/testLists/{testListId}")
    public TestList updateTestList(@PathVariable Long testListId,
                           @Valid @RequestBody TestList testListRequest) {
        return testListRepository.findById(testListId)
                .map(testList -> {
                    testList.setName(testListRequest.getName());
                    testList.setSubject(testListRequest.getSubject());
                    testList.setUser(testListRequest.getUser());
                    return testListRepository.save(testList);
                }).orElseThrow(() -> new ResourceNotFoundException("TestList not found with Id " + testListId));
    }

    @DeleteMapping("/testLists/{testListId}")
    public ResponseEntity<?> deleteTestListByUserId(@PathVariable Long testListId) {
        return testListRepository.findById(testListId)
                .map(testList -> {
                    testListRepository.delete(testList);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("TestList not found with Id " + testListId));
    }
}
