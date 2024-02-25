package com.app.bdt.service.serviceImpl;


import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.*;
import com.app.bdt.model.mapper.IMasterMapper;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.model.response.ILanguagesTalent;
import com.app.bdt.model.response.ITalentMasterDataResponse;
import com.app.bdt.repository.*;
import com.app.bdt.service.ITalentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@Transactional
public class TalentService implements ITalentService {

  private final ITalentRepository talentRepository;
  private final ITechnicalSkillRepository technicalSkillRepository;
  private final ISoftSkillRepository softSkillRepository;
  private final IWorkExperienceRepository workExperienceRepository;
  private final IEducationalExperienceRepository educationalExperienceRepository;
  private final ILanguageTalentMasterRepository languageTalentMasterRepository;
  private final IMasterRepository masterRepository;
  private final ITalentMasterRepository talentMasterRepository;
  private final ITalentMapper talentMapper;
  private final IMasterMapper masterMapper;


  Logger log = Logger.getLogger(this.getClass().getName());

  public TalentService(ITalentRepository talentRepository, ITechnicalSkillRepository technicalSkillRepository, ISoftSkillRepository softSkillRepository, IWorkExperienceRepository workExperienceRepository, IEducationalExperienceRepository educationalExperienceRepository, ILanguageTalentMasterRepository languageTalentMasterRepository, IMasterRepository masterRepository, ITalentMasterRepository talentMasterRepository, ITalentMapper talentMapper, IMasterMapper masterMapper) {
    this.talentRepository = talentRepository;
    this.technicalSkillRepository = technicalSkillRepository;
    this.softSkillRepository = softSkillRepository;
    this.workExperienceRepository = workExperienceRepository;
    this.educationalExperienceRepository = educationalExperienceRepository;
    this.languageTalentMasterRepository = languageTalentMasterRepository;
    this.masterRepository = masterRepository;
    this.talentMasterRepository = talentMasterRepository;
    this.talentMapper = talentMapper;
    this.masterMapper = masterMapper;
  }

  @Override
  public List<TalentDto> getTalents() {
    try {
      List<Talent> talentsList = talentRepository.findAllTalents();
      return talentsList.stream()
              .map(this::getBuiltTalentDto)
              .collect(Collectors.toList());
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
      saveLanguages(talent, createdTalent.getId());
      int[] data = {talentRequest.getCountryId(), talentRequest.getCityId(), talentRequest.getCurrencyId(), talentRequest.getProfileId()};
      saveCountryCityProfileCurrency(createdTalent.getId(), data);
      return getBuiltTalentDto(createdTalent);
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

  private void saveLanguages(Talent talent, Long talentId) {
    for (LanguageTalentMaster languageTalentMaster : talent.getLanguagesList()) {
      languageTalentMasterRepository.createMasterTalentLanguage(talentId, languageTalentMaster);
    }
  }

  private void saveCountryCityProfileCurrency(Long talentId, int[] data) {
    TalentMaster talentMaster = new TalentMaster();
    /*Add country*/
    talentMaster.setIdMaster(1);
    talentMaster.setId(data[0]);
    talentMasterRepository.addCountry(talentId, talentMaster);

    /*Add city*/
    talentMaster.setIdMaster(2);
    talentMaster.setId(data[1]);
    talentMasterRepository.addCity(talentId, talentMaster);

    /*Add currency*/
    talentMaster.setIdMaster(1);
    talentMaster.setId(data[2]);
    talentMasterRepository.addCurrency(talentId, talentMaster);

    /*Add profile*/
    talentMaster.setIdMaster(1);
    talentMaster.setId(data[3]);
    talentMasterRepository.addProfile(talentId, talentMaster);
  }

  private TalentDto getBuiltTalentDto(Talent talent) {
    TalentDto talentDto = talentMapper.toTalentDto(talent);
    List<ILanguagesTalent> languagesTalent = languageTalentMasterRepository.findAllLanguagesTalents().stream()
            .filter(languageTalent -> Objects.equals(languageTalent.getTalentId(), talent.getId()))
            .collect(Collectors.toList());
    for (ITalentMasterDataResponse talentMasterDataResponse : talentMasterRepository.getMasterDataOfTalents()) {
      if (talent.getId() == talentMasterDataResponse.getTalentId()) {
        talentDto.setCountry(talentMasterDataResponse.getCountry());
        talentDto.setCity(talentMasterDataResponse.getCity());
        talentDto.setCurrency(talentMasterDataResponse.getCurrency());
        talentDto.setProfile(talentMasterDataResponse.getProfile());
      }
    }
    talentDto.setLanguageList(masterMapper.toLanguageDtoList(languagesTalent));
    return talentDto;
  }

}
