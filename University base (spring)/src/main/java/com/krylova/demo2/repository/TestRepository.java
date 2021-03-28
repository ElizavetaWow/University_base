package com.krylova.demo2.repository;

import com.krylova.demo2.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findByTestListId(Long testListId);
    List<Test> findByQuestionId(Long questionId);
}
