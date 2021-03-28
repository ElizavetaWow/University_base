package com.krylova.demo2.repository;

import com.krylova.demo2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByGroupId(Long groupId);
    List<User> findByRoleId(Long roleId);
}
