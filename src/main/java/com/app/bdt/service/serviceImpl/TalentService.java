package com.app.bdt.service.serviceImpl;

import com.app.bdt.exceptions.BadRequestException;
import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.dto.LanguageDto;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.*;
import com.app.bdt.model.mapper.IMasterMapper;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.model.request.*;
import com.app.bdt.model.response.ILanguagesTalent;
import com.app.bdt.model.response.ITalentResponse;
import com.app.bdt.model.response.Response;
import com.app.bdt.model.response.TalentCardResponse;
import com.app.bdt.repository.ITalentMasterRepository;
import com.app.bdt.repository.ITalentRepository;
import com.app.bdt.service.ITalentService;
import com.app.bdt.util.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
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
  public List<TalentDto> getAllTalents() {
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
  public Optional<TalentDto> getTalentDtoById(Long talentId) {
    try {
      Optional<Talent> result = talentRepository.findTalentById(talentId);
      return result.map(this::getBuiltTalentDto);
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<TalentCardResponse> getAllTalentsWithBasicData() {
    try {
      return talentMapper.toTalentCardResponseList(getAllTalents());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  private Optional<Talent> getTalentById(Long talentId) {
    return talentRepository.findTalentById(talentId);
  }

  @Override
  public TalentDto create(TalentRequest talentRequest) {
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
      //Talent foundTalent = talentRepository.findTalentById(talentId);
      Talent foundTalent = null;
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
  public Response updateSocials(Long talentId, SocialRequest socialRequest) {
    Talent talent = getTalentById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    try {
      talent.setGithubLink(socialRequest.getGithubLink());
      talent.setLinkedinLink(socialRequest.getLinkedinLink());
      talentRepository.updateTalent(talent.getId(), talent);
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_UPDATE.getMessage());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response updateSalaryBand(Long talentId, SalaryBandRequest salaryBandRequest) {
    Talent talent = getTalentById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    try {
      talent.setInitialAmount(salaryBandRequest.getInitialAmount());
      talent.setFinalAmount(salaryBandRequest.getFinalAmount());
      talentRepository.updateTalent(talent.getId(), talent);
      talentRepository.updateCurrency(talent.getId(), salaryBandRequest.getCurrencyId());
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_UPDATE.getMessage());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response updateDescription(Long talentId, String description) {
    Talent talent = getTalentById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    try {
      talent.setDescription(description);
      talentRepository.updateTalent(talent.getId(), talent);
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_UPDATE.getMessage());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response addTechnicalSkill(Long talentId, TechnicalSkillRequest technicalSkillRequest) {
    Optional<Talent> talent = getTalentById(talentId);
    if (talent.isPresent()) {
      try {
        String skill = technicalSkillRequest.getSkill();
        Integer years = technicalSkillRequest.getYears();
        talentRepository.addTechnicalSkill(talent.get().getId(), TechnicalSkill.builder().skill(skill).years(years).build());
        return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
      } catch (RuntimeException e) {
        throw new InternalServerError(e.getMessage());
      }
    } else {
      throw new NotFoundException(Messages.NOT_FOUND.getMessage());
    }
  }

  @Override
  public Response addSoftSkill(Long talentId, SoftSkillRequest softSkillRequest) {
    Optional<Talent> talent = getTalentById(talentId);
    if (talent.isPresent()) {
      try {
        String skill = softSkillRequest.getSkill();
        talentRepository.addSoftSkill(talent.get().getId(), SoftSkill.builder().skill(skill).build());
        return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
      } catch (RuntimeException e) {
        throw new InternalServerError(e.getMessage());
      }
    } else {
      throw new NotFoundException(Messages.NOT_FOUND.getMessage());
    }
  }

  @Override
  public Response addWorkExperience(Long talentId, WorkExperienceRequest workExperienceRequest) {
    Talent talent = getTalentById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    try {
      talentRepository.addWorkExperience(talent.getId(), talentMapper.toWorkExperience(workExperienceRequest));
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response addEducationalExperience(Long talentId, EducationalExperienceRequest educationalExperienceRequest) {
    Talent talent = getTalentById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    try {
      talentRepository.addEducationalExperience(talent.getId(), talentMapper.toEducationalExperience(educationalExperienceRequest));
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response addLanguage(Long talentId, LanguageRequest languageRequest) {
    try {
      TalentDto talentDto = getTalentDtoById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
      List<LanguageDto> languagesOfTalent = talentDto.getLanguagesList();
      boolean hasLanguage = languagesOfTalent.stream().anyMatch(language -> language.getLanguageId().equals(languageRequest.getLanguageId()));
      if (hasLanguage) {
        throw new BadRequestException(Messages.ALREADY_REGISTERED.getMessage());
      }
      talentRepository.addLanguage(talentId, talentMapper.toLanguage(languageRequest));
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
    } catch (BadRequestException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response updateWorkExperience(Long talentId, Long workExperienceId, WorkExperienceRequest workExperienceRequest) {
    TalentDto talentDto = getTalentDtoById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    boolean workExperienceExists = talentDto.getWorkExperiencesList().stream()
            .anyMatch(workExperienceDto -> workExperienceDto.getId().equals(workExperienceId));
    if (workExperienceExists) {
      try {
        talentRepository.updateWorkExperience(talentId, workExperienceId, workExperienceRequest);
        return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_UPDATE.getMessage());
      } catch (RuntimeException e) {
        throw new InternalServerError(e.getMessage());
      }
    } else {
      throw new NotFoundException(Messages.NOT_FOUND.getMessage());
    }
  }

  @Override
  public Response updateEducationalExperience(Long talentId, Long educationalExperienceId, EducationalExperienceRequest educationalExperienceRequest) {
    TalentDto talentDto = getTalentDtoById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    boolean educationalExperienceExists = talentDto.getEducationalExperiencesList().stream()
            .anyMatch(educationalExperienceDto -> educationalExperienceDto.getId().equals(educationalExperienceId));
    if (educationalExperienceExists) {
      try {
        talentRepository.updateEducationalExperience(talentId, educationalExperienceId, educationalExperienceRequest);
        return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_UPDATE.getMessage());
      } catch (RuntimeException e) {
        throw new InternalServerError(e.getMessage());
      }
    } else {
      throw new NotFoundException(Messages.NOT_FOUND.getMessage());
    }
  }

  @Override
  public List<TalentCardResponse> getByTechnicalSkillsLanguageAndLevel(Map<String, Object> params) {
    try {
      Optional<Integer> languageId = Optional.ofNullable((Integer) params.get("languageId"));
      Optional<Integer> levelId = Optional.ofNullable((Integer) params.get("levelId"));
      Optional<List<String>> technicalSkills = Optional.ofNullable((List<String>) params.get("technicalSkills"));
      List<ITalentResponse> result;
      if (languageId.isPresent() || levelId.isPresent() || technicalSkills.isPresent()) {
        result = talentRepository.findTalentsIdsByTechnicalSkillsLanguageIdAndLevelId(
                languageId.orElse(null),
                levelId.orElse(null),
                technicalSkills.map(skills -> String.join(",", skills)).orElse(null)
        );
      } else {
        result = talentRepository.findTalentsIdsByTechnicalSkillsLanguageIdAndLevelId(null, null, null);
      }
      Set<ITalentResponse> res = new HashSet<>(result);
      return talentMapper.toTalentCardResponseList(getAllTalents().stream().filter(
              talent -> res.stream().anyMatch(
                      obj -> obj.getTalentId().equals(talent.getId()))).collect(Collectors.toList()));
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
            .filter(talentMasterDataResponse -> Objects.equals(talent.getId(), talentMasterDataResponse.getTalentId()))
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
