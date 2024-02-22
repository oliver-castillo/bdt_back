package com.app.bdt.service.serviceImpl;


import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.*;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.repository.*;
import com.app.bdt.service.ITalentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class TalentService implements ITalentService {

  private final ITalentRepository talentRepository;
  private final ITechnicalSkillRepository technicalSkillRepository;
  private final ISoftSkillRepository softSkillRepository;
  private final IWorkExperienceRepository workExperienceRepository;

  private final IEducationalExperienceRepository educationalExperienceRepository;
  private final ITalentMapper talentMapper;

  Logger log = Logger.getLogger(this.getClass().getName());

  public TalentService(ITalentRepository talentRepository, ITechnicalSkillRepository technicalSkillRepository, ISoftSkillRepository softSkillRepository, IWorkExperienceRepository workExperienceRepository, IEducationalExperienceRepository educationalExperienceRepository, ITalentMapper talentMapper) {
    this.talentRepository = talentRepository;
    this.technicalSkillRepository = technicalSkillRepository;
    this.softSkillRepository = softSkillRepository;
    this.workExperienceRepository = workExperienceRepository;
    this.educationalExperienceRepository = educationalExperienceRepository;
    this.talentMapper = talentMapper;
  }

  @Override
  public List<TalentDto> getTalents() {
    try {
      return talentMapper.toTalentDtoList(talentRepository.findAllTalents());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public TalentDto createTalent(TalentRequest talentRequest) {
    try {
      Talent talent = talentMapper.toTalent(talentRequest);
      talentRepository.createTalent(talentMapper.toTalent(talentRequest));
      Talent createdTalent = talentRepository.getLastInsertedTalent();
      saveSkillsAndExperiences(talent, createdTalent.getId());
      System.out.println("TALENT: " + talent);
      System.out.println("LAST TALENT: " + createdTalent);
      return talentMapper.toTalentDto(createdTalent);
    } catch (RuntimeException e) {
      log.warning("HERE = " + e.getMessage());
      throw new InternalServerError(e.getMessage());
    }
  }

  private void saveSkillsAndExperiences(Talent talent, Long talentId) {
    for (TechnicalSkill technicalSkill : talent.getTechnicalSkillsList()) {
      technicalSkillRepository.createTechnicalSkill(talentId, technicalSkill);
    }
    for (SoftSkill softSkill : talent.getSoftSkillsList()) {
      softSkillRepository.createSoftSkill(talentId, softSkill);
    }
    for (WorkExperience workExperience : talent.getWorkExperiencesList()) {
      workExperienceRepository.createWorkExperience(talentId, workExperience);
    }
    for (EducationalExperience educationalExperience : talent.getEducationalExperiencesList()) {
      educationalExperienceRepository.createEducationalExperience(talentId, educationalExperience);
    }
  }

}
