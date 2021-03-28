package com.krylova.demo2.repository;

import com.krylova.demo2.model.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer, Long> {
    List<StudentAnswer> findByTestListId(Long testId);
    List<StudentAnswer> findByUserId(Long userId);
    List<StudentAnswer> findByAnswerId(Long answerId);
}
