package com.app.bdt.service.serviceImpl;

import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.*;
import com.app.bdt.model.mapper.IMasterMapper;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.model.response.ILanguagesTalent;
import com.app.bdt.model.response.ITalentByLanguageAndLevel;
import com.app.bdt.model.response.ITalentByTechnicalSkills;
import com.app.bdt.repository.ITalentMasterRepository;
import com.app.bdt.repository.ITalentRepository;
import com.app.bdt.service.ITalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class TalentService implements ITalentService {

  private final ITalentRepository talentRepository;
  private final ITalentMasterRepository talentMasterRepository;
  private final ITalentMapper talentMapper;
  private final IMasterMapper masterMapper;

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
      /* Add technical skills */
      for (TechnicalSkill technicalSkill : talent.getTechnicalSkillsList()) {
        talentRepository.addTechnicalSkill(createdTalent.getId(), technicalSkill);
      }
      /* Add soft skills */
      for (SoftSkill softSkill : talent.getSoftSkillsList()) {
        talentRepository.addSoftSkill(createdTalent.getId(), softSkill);
      }
      /* Add work experiences */
      for (WorkExperience workExperience : talent.getWorkExperiencesList()) {
        talentRepository.addWorkExperience(createdTalent.getId(), workExperience);
      }
      /* Add educational experiences */
      for (EducationalExperience educationalExperience : talent.getEducationalExperiencesList()) {
        talentRepository.addEducationalExperience(createdTalent.getId(), educationalExperience);
      }
      /* Add languages */
      for (Language language : talent.getLanguagesList()) {
        talentRepository.addLanguage(createdTalent.getId(), language);
      }
      /* Add files */
      for (File file : talent.getFilesList()) {
        talentRepository.addFile(createdTalent.getId(), file);
      }
      /* Add country */
      talentMasterRepository.addCountry(createdTalent.getId(), talentRequest.getCountryId());
      /* Add city */
      talentMasterRepository.addCity(createdTalent.getId(), talentRequest.getCityId());
      /* Add currency */
      talentMasterRepository.addCurrency(createdTalent.getId(), talentRequest.getCurrencyId());
      /* Add profile */
      talentMasterRepository.addProfile(createdTalent.getId(), talentRequest.getProfileId());

      return getBuiltTalentDto(createdTalent);
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public TalentDto updateTalent(Long talentId, TalentRequest talentRequest) {
    try {
      Talent foundTalent = talentRepository.findTalentById(talentId);
      if (talentRequest != null) {
        Talent talent = talentMapper.toTalent(talentRequest);
        Talent talentToUpdate = new Talent();
        talentToUpdate.setName(
                (talent.getName() != null && !talent.getName().isEmpty())
                        ? talent.getName() : foundTalent.getName());
        talentToUpdate.setPaternalSurname(
                (talent.getPaternalSurname() != null && !talent.getPaternalSurname().isEmpty())
                        ? talent.getPaternalSurname() : foundTalent.getPaternalSurname());
        talentToUpdate.setMaternalSurname(
                (talent.getMaternalSurname() != null && !talent.getMaternalSurname().isEmpty())
                        ? talent.getMaternalSurname() : foundTalent.getMaternalSurname());
        talentToUpdate.setImage(
                (talent.getImage() != null)
                        ? talent.getImage() : foundTalent.getImage());
        talentToUpdate.setDescription(
                (talent.getDescription() != null && !talent.getDescription().isEmpty())
                        ? talent.getDescription() : foundTalent.getDescription());
        talentToUpdate.setInitialAmount(
                (talent.getInitialAmount() != null)
                        ? talent.getInitialAmount() : foundTalent.getInitialAmount());
        talentToUpdate.setFinalAmount(
                (talent.getFinalAmount() != null)
                        ? talent.getFinalAmount() : foundTalent.getFinalAmount());
        talentToUpdate.setCellPhoneNumber(
                (talent.getCellPhoneNumber() != null && !talent.getCellPhoneNumber().isEmpty())
                        ? talent.getCellPhoneNumber() : foundTalent.getCellPhoneNumber());
        talentToUpdate.setLinkedinLink(
                (talent.getLinkedinLink() != null && !talent.getLinkedinLink().isEmpty())
                        ? talent.getLinkedinLink() : foundTalent.getLinkedinLink());
        talentToUpdate.setGithubLink(
                (talent.getGithubLink() != null && !talent.getGithubLink().isEmpty())
                        ? talent.getGithubLink() : foundTalent.getGithubLink());
        return getBuiltTalentDto(talentToUpdate);
      } else {
        throw new NotFoundException("No se encontró el registro");
      }
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<Map<String, Object>> getTalentsByTechnicalSkills(List<String> technicalSkills) {
    try {
      List<ITalentByTechnicalSkills> talentsAndTechnicalSkills = talentRepository.findTalentsByTechnicalSkills(String.join(",", technicalSkills));
      return talentsAndTechnicalSkills.stream()
              .collect(Collectors.groupingBy(ITalentByTechnicalSkills::getTalentId, Collectors.mapping(ITalentByTechnicalSkills::getTechnicalSkill, Collectors.toList())))
              .entrySet()
              .stream()
              .map(entry -> {
                Map<String, Object> map = new HashMap<>();
                map.put("talentId", entry.getKey());
                map.put("technicalSkills", entry.getValue());
                return map;
              })
              .collect(Collectors.toList());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<ITalentByLanguageAndLevel> getTalentsByLanguageAndLevel(int languageId, int levelId) {
    try {
      return talentRepository.findTalentsByLanguageAndLevel(languageId, levelId);
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }


  private TalentDto getBuiltTalentDto(Talent talent) {
    TalentDto talentDto = talentMapper.toTalentDto(talent);

    List<ILanguagesTalent> languagesTalent = talentMasterRepository.findAllLanguagesOfTalents().stream()
            .filter(languageTalent -> Objects.equals(languageTalent.getTalentId(), talent.getId()))
            .collect(Collectors.toList());

    talentDto.setLanguagesList(masterMapper.toLanguageDtoList(languagesTalent));

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
