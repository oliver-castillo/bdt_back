package com.app.bdt.repository;

import com.app.bdt.model.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITalentFeedbackRepository extends JpaRepository<Feedback, Long> {

}
