package com.vignesh.conceptile_assignment.repos;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vignesh.conceptile_assignment.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query(value = "SELECT * FROM Question q ORDER BY RAND() LIMIT 1",nativeQuery = true)
    Optional<Question> findRandomQuestion();
}
