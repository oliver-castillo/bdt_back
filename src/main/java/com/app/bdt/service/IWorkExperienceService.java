package com.app.bdt.service;

import java.time.LocalDate;

public interface IWorkExperienceService {

    void createWorkExperience(Long talentId, String companyName, String position, LocalDate startDate, LocalDate endDate);

}
