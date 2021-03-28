package com.krylova.demo2.repository;

import com.krylova.demo2.model.TestList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestListRepository extends JpaRepository<TestList, Long> {
    List<TestList> findBySubjectId(Long subjectId);
    List<TestList> findByUserId(Long userId);
}
