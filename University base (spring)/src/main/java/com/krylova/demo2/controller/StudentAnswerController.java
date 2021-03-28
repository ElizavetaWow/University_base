package com.krylova.demo2.controller;

import com.krylova.demo2.exception.ResourceNotFoundException;
import com.krylova.demo2.model.StudentAnswer;
import com.krylova.demo2.repository.AnswerRepository;
import com.krylova.demo2.repository.StudentAnswerRepository;
import com.krylova.demo2.repository.TestListRepository;
import com.krylova.demo2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentAnswerController {

    @Autowired
    private StudentAnswerRepository studentAnswerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestListRepository testListRepository;

    @GetMapping("/studentAnswers")
    public Page<StudentAnswer> getAllStudentAnswers(Pageable pageable) {
        return studentAnswerRepository.findAll(pageable);
    }

    @GetMapping("/users/{userId}/studentAnswers")
    public List<StudentAnswer> getStudentAnswersByUserId(Long userId) {
        return studentAnswerRepository.findByUserId(userId);
    }

    @GetMapping("/answers/{answerId}/studentAnswers")
    public List<StudentAnswer> getStudentAnswersByAnswerId(Long answerId) {
        return studentAnswerRepository.findByAnswerId(answerId);
    }

    @GetMapping("/testLists/{testListId}/studentAnswers")
    public List<StudentAnswer> getStudentAnswersByTestListId(Long testListId) {
        return studentAnswerRepository.findByTestListId(testListId);
    }

    @PostMapping("/studentAnswers")
    public StudentAnswer createStudentAnswer(@Valid @RequestBody StudentAnswer studentAnswer) {
        return studentAnswerRepository.save(studentAnswer);
    }

    @PostMapping("/users/{userId}/studentAnswers")
    public StudentAnswer addStudentAnswerToUser(@PathVariable Long userId, @Valid @RequestBody StudentAnswer studentAnswer) {
        return userRepository.findById(userId)
                .map(user -> {
                    studentAnswer.setUser(user);
                    return studentAnswerRepository.save(studentAnswer);
                }).orElseThrow(() -> new ResourceNotFoundException("User not found with Id " + userId));
    }

    @PostMapping("/answers/{answerId}/studentAnswers")
    public StudentAnswer addStudentAnswerToAnswer(@PathVariable Long answerId, @Valid @RequestBody StudentAnswer studentAnswer) {
        return answerRepository.findById(answerId)
                .map(answer -> {
                    studentAnswer.setAnswer(answer);
                    return studentAnswerRepository.save(studentAnswer);
                }).orElseThrow(() -> new ResourceNotFoundException("Answer not found with Id " + answerId));
    }

    @PostMapping("/testLists/{testListId}/studentAnswers")
    public StudentAnswer addStudentAnswerToTestList(@PathVariable Long testListId,
                                                    @Valid @RequestBody StudentAnswer studentAnswer) {
        return testListRepository.findById(testListId)
                .map(testList -> {
                    studentAnswer.setTestlist(testList);
                    return studentAnswerRepository.save(studentAnswer);
                }).orElseThrow(() -> new ResourceNotFoundException("TestList not found with Id " + testListId));
    }

    @PutMapping("/studentAnswers/{studentAnswerId}")
    public StudentAnswer updateStudentAnswer(@PathVariable Long studentAnswerId,
                                   @Valid @RequestBody StudentAnswer studentAnswerRequest) {
        return studentAnswerRepository.findById(studentAnswerId)
                .map(studentAnswer -> {
                    studentAnswer.setTestlist(studentAnswerRequest.getTestlist());
                    studentAnswer.setAnswer(studentAnswerRequest.getAnswer());
                    studentAnswer.setUser(studentAnswerRequest.getUser());
                    return studentAnswerRepository.save(studentAnswer);
                }).orElseThrow(() -> new ResourceNotFoundException("StudentAnswer not found with Id " + studentAnswerId));
    }

    @DeleteMapping("/studentAnswers/{studentAnswerId}")
    public ResponseEntity<?> deleteStudentAnswerByUserId(@PathVariable Long studentAnswerId) {
        return studentAnswerRepository.findById(studentAnswerId)
                .map(studentAnswer -> {
                    studentAnswerRepository.delete(studentAnswer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("StudentAnswer not found with Id " + studentAnswerId));
    }
}
