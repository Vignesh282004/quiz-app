package com.vignesh.conceptile_assignment.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vignesh.conceptile_assignment.model.UserAnswer;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Long> {
    List<UserAnswer> findByUserId(Long userId);
}
