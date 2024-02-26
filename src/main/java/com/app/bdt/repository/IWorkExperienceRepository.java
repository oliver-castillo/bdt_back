package com.app.bdt.repository;

import com.app.bdt.model.entity.WorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

}
