package com.app.bdt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.bdt.model.entity.WorkExperience;

@Repository
public interface IWorkExperienceRepository extends JpaRepository<WorkExperience, Long> {

}
