package com.app.bdt.service.serviceImpl;


import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.*;
import com.app.bdt.model.mapper.IMasterMapper;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.model.response.ILanguagesTalent;
import com.app.bdt.repository.ITalentMasterRepository;
import com.app.bdt.repository.ITalentRepository;
import com.app.bdt.service.ITalentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class TalentService implements ITalentService {

  private final ITalentRepository talentRepository;
  private final ITalentMasterRepository talentMasterRepository;
  private final ITalentMapper talentMapper;
  private final IMasterMapper masterMapper;

  public TalentService(ITalentRepository talentRepository, ITalentMasterRepository talentMasterRepository, ITalentMapper talentMapper, IMasterMapper masterMapper) {
    this.talentRepository = talentRepository;
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
      talentRepository.createTalent(talent);
      Talent createdTalent = talentRepository.getLastInsertedTalent();
      /*Add technical skills*/
      for (TechnicalSkill technicalSkill : talent.getTechnicalSkillsList()) {
        talentRepository.addTechnicalSkill(createdTalent.getId(), technicalSkill);
      }
      /*Add soft skills*/
      for (SoftSkill softSkill : talent.getSoftSkillsList()) {
        talentRepository.addSoftSkill(createdTalent.getId(), softSkill);
      }
      /*Add work experiences*/
      for (WorkExperience workExperience : talent.getWorkExperiencesList()) {
        talentRepository.addWorkExperience(createdTalent.getId(), workExperience);
      }
      /*Add educational experiences*/
      for (EducationalExperience educationalExperience : talent.getEducationalExperiencesList()) {
        talentRepository.addEducationalExperience(createdTalent.getId(), educationalExperience);
      }
      /*Add languages*/
      for (Language language : talent.getLanguagesList()) {
        talentRepository.addLanguage(createdTalent.getId(), language);
      }
      /*Add country*/
      talentMasterRepository.addCountry(createdTalent.getId(), talentRequest.getCountryId());
      /*Add city*/
      talentMasterRepository.addCity(createdTalent.getId(), talentRequest.getCityId());
      /*Add currency*/
      talentMasterRepository.addCurrency(createdTalent.getId(), talentRequest.getCurrencyId());
      /*Add profile*/
      talentMasterRepository.addProfile(createdTalent.getId(), talentRequest.getProfileId());

      return getBuiltTalentDto(createdTalent);
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  private TalentDto getBuiltTalentDto(Talent talent) {
    TalentDto talentDto = talentMapper.toTalentDto(talent);

    List<ILanguagesTalent> languagesTalent = talentMasterRepository.findAllLanguagesOfTalents().stream()
            .filter(languageTalent -> Objects.equals(languageTalent.getTalentId(), talent.getId()))
            .collect(Collectors.toList());

    talentDto.setLanguageList(masterMapper.toLanguageDtoList(languagesTalent));

    talentMasterRepository.getMasterDataOfTalents().stream()
            .filter(talentMasterDataResponse -> talent.getId() == talentMasterDataResponse.getTalentId())
            .findFirst()
            .ifPresent(talentMasterDataResponse -> {
              talentDto.setCountry(talentMasterDataResponse.getCountry());
              talentDto.setCity(talentMasterDataResponse.getCity());
              talentDto.setCurrency(talentMasterDataResponse.getCurrency());
              talentDto.setProfile(talentMasterDataResponse.getProfile());
            });

    return talentDto;
  }

}
