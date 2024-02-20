package com.app.bdt.service;

import java.time.LocalDate;

public interface IEducationalExperienceService {
    void createEducationalExperience(Long talentId, String educationalInstitute, String career, String degree, LocalDate startDate, LocalDate endDate);
}
