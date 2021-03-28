package com.krylova.demo2.controller;

import com.krylova.demo2.exception.ResourceNotFoundException;
import com.krylova.demo2.model.Test;
import com.krylova.demo2.repository.TestListRepository;
import com.krylova.demo2.repository.TestRepository;
import com.krylova.demo2.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TestController {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestListRepository testListRepository;

    @GetMapping("/tests")
    public Page<Test> getAllTests(Pageable pageable) {
        return testRepository.findAll(pageable);
    }

    @GetMapping("/questions/{questionId}/tests")
    public List<Test> getTestsByQuestionId(Long questionId) {
        return testRepository.findByQuestionId(questionId);
    }

    @GetMapping("/testLists/{testListId}/tests")
    public List<Test> getTestsByTestListId(Long testListId) {
        return testRepository.findByTestListId(testListId);
    }

    @PostMapping("/tests")
    public Test createTest(@Valid @RequestBody Test test) {
        return testRepository.save(test);
    }

    @PostMapping("/questions/{questionId}/tests")
    public Test addTestToQuestion(@PathVariable Long questionId, @Valid @RequestBody Test test) {
        return questionRepository.findById(questionId)
                .map(question -> {
                    test.setQuestion(question);
                    return testRepository.save(test);
                }).orElseThrow(() -> new ResourceNotFoundException("Question not found with Id " + questionId));
    }

    @PostMapping("/testLists/{testListId}/tests")
    public Test addTestToTestList(@PathVariable Long testListId, @Valid @RequestBody Test test) {
        return testListRepository.findById(testListId)
                .map(testList -> {
                    test.setTestList(testList);
                    return testRepository.save(test);
                }).orElseThrow(() -> new ResourceNotFoundException("TestList not found with Id " + testListId));
    }

    @PutMapping("/tests/{testId}")
    public Test updateTest(@PathVariable Long testId,
                                   @Valid @RequestBody Test testRequest) {
        return testRepository.findById(testId)
                .map(test -> {
                    test.setTestList(testRequest.getTestList());
                    test.setQuestion(testRequest.getQuestion());
                    return testRepository.save(test);
                }).orElseThrow(() -> new ResourceNotFoundException("Test not found with Id " + testId));
    }

    @DeleteMapping("/tests/{testId}")
    public ResponseEntity<?> deleteTestByQuestionId(@PathVariable Long testId) {
        return testRepository.findById(testId)
                .map(test -> {
                    testRepository.delete(test);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Test not found with Id " + testId));
    }
}
