package com.app.bdt.repository;

import com.app.bdt.model.entity.EducationalExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducationalExperienceRepository extends JpaRepository<EducationalExperience, Long> {

}
