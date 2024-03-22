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
import com.app.bdt.model.response.ILanguagesTalentResponse;
import com.app.bdt.model.response.ITalentResponse;
import com.app.bdt.model.response.Response;
import com.app.bdt.model.response.TalentCardResponse;
import com.app.bdt.repository.ITalentMasterRepository;
import com.app.bdt.repository.ITalentRepository;
import com.app.bdt.service.ITalentService;
import com.app.bdt.service.IUserService;
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
  private final IUserService userService;

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

  private void validSkillsList(List<TechnicalSkill> technicalSkills, List<SoftSkill> softSkills) {
    Set<String> uniqueTechnicalSkills = new HashSet<>();
    Set<String> uniqueSoftSkills = new HashSet<>();
    boolean distinctTechnicalSkills = technicalSkills.stream().allMatch(skill -> uniqueTechnicalSkills.add(skill.getSkill().toUpperCase()));
    boolean distinctSoftSkills = softSkills.stream().allMatch(skill -> uniqueSoftSkills.add(skill.getSkill().toUpperCase()));
    if (!distinctTechnicalSkills || !distinctSoftSkills) {
      throw new BadRequestException(Messages.REPEATED_DATA.getMessage());
    }
  }

  @Override
  public TalentDto create(TalentRequest talentRequest) {
    try {
      Talent talent = talentMapper.toTalent(talentRequest);
      talentRepository.createTalent(talent);
      Talent createdTalent = talentRepository.getLastInsertedTalent();
      validSkillsList(talent.getTechnicalSkillsList(), talent.getSoftSkillsList());
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
        file.setFileName("CV");
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
    } catch (BadRequestException e) {
      throw e;
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
  public Response addFile(Long talentId, FileRequest fileRequest) {
    Talent talent = getTalentById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    try {
      fileRequest.setFileName("FILE_" + fileRequest.getFileName());
      talentRepository.addFile(talent.getId(), talentMapper.toFile(fileRequest));
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
    } catch (NotFoundException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response addTechnicalSkill(Long talentId, TechnicalSkillRequest technicalSkillRequest) {
    TalentDto talentDto = getTalentDtoById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    try {
      String skill = technicalSkillRequest.getSkill();
      Integer years = technicalSkillRequest.getYears();
      boolean skillExists = talentDto.getTechnicalSkillsList().stream().anyMatch(obj -> obj.getSkill().equalsIgnoreCase(skill));
      if (skillExists) {
        throw new BadRequestException(Messages.ALREADY_REGISTERED.getMessage());
      }
      talentRepository.addTechnicalSkill(talentDto.getId(), TechnicalSkill.builder().skill(skill).years(years).build());
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
    } catch (NotFoundException | BadRequestException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response addSoftSkill(Long talentId, SoftSkillRequest softSkillRequest) {
    TalentDto talentDto = getTalentDtoById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
    try {
      String skill = softSkillRequest.getSkill();
      boolean skillExists = talentDto.getSoftSkillsList().stream().anyMatch(obj -> obj.getSkill().equalsIgnoreCase(skill));
      if (skillExists) {
        throw new BadRequestException(Messages.ALREADY_REGISTERED.getMessage());
      }
      talentRepository.addSoftSkill(talentDto.getId(), SoftSkill.builder().skill(skill).build());
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
    } catch (NotFoundException | BadRequestException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
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
  public Response addFeedback(FeedbackRequest feedbackRequest) {
    try {
      talentRepository.addFeedback(feedbackRequest);
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_INSERT.getMessage());
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
  public Response updateLanguage(Long talentId, Long id, LanguageRequest languageRequest) {
    try {
      TalentDto talentDto = getTalentDtoById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
      boolean languageExists = talentDto.getLanguagesList().stream().anyMatch(language -> Objects.equals(language.getId(), id));
      if (languageExists) {
        talentRepository.updateLanguage(talentId, id, talentMapper.toLanguage(languageRequest));
        return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_UPDATE.getMessage());
      } else {
        throw new NotFoundException(Messages.NOT_FOUND.getMessage());
      }
    } catch (NotFoundException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response updateImage(Long talentId, ImageRequest imageRequest) {
    try {
      Talent talent = getTalentById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
      talent.setImage(talentMapper.stringToByteArray(imageRequest.getImage()));
      talentRepository.updateTalent(talentId, talent);
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_UPDATE.getMessage());
    } catch (NotFoundException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response updateCV(Long talentId, Long fileId, FileRequest fileRequest) {
    try {
      Talent talent = getTalentById(talentId).orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
      File cvFile = talent.getFilesList().stream().filter(
                      file -> file.getId().equals(fileId) && file.getFileName().equalsIgnoreCase("CV"))
              .findFirst().get();

      File newFile = talentMapper.toFile(fileRequest);

      cvFile.setFileName("CV");
      cvFile.setFileType(newFile.getFileType());
      cvFile.setFileInBytes(newFile.getFileInBytes());

      talentRepository.updateFile(talentId, cvFile);
      return new Response(HttpStatus.OK.value(), Messages.SUCCESSFUL_UPDATE.getMessage());
    } catch (BadRequestException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
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
      List<TalentCardResponse> filteredList = talentMapper.toTalentCardResponseList(getAllTalents().stream().filter(
              talent -> res.stream().anyMatch(
                      obj -> obj.getTalentId().equals(talent.getId()))).collect(Collectors.toList()));
      if (params.get("data") != null) {
        return filteredList.stream().filter(obj ->
                obj.getName().toLowerCase().contains(params.get("data").toString().toLowerCase()) ||
                        obj.getPaternalSurname().toLowerCase().contains(params.get("data").toString().toLowerCase()) ||
                        obj.getMaternalSurname().toLowerCase().contains(params.get("data").toString().toLowerCase()) ||
                        obj.getProfile().toLowerCase().contains(params.get("data").toString().toLowerCase())).collect(Collectors.toList());
      }
      return filteredList;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public List<TalentCardResponse> getByIds(Set<Long> ids) {
    try {
      return getAllTalentsWithBasicData().stream()
              .filter(obj -> ids.contains(obj.getId())).collect(Collectors.toList());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  private TalentDto getBuiltTalentDto(Talent talent) {
    TalentDto talentDto = talentMapper.toTalentDto(talent);
    List<ILanguagesTalentResponse> languagesTalent = talentMasterRepository.findAllLanguagesOfTalents().stream()
            .filter(languageTalent -> Objects.equals(languageTalent.getTalentId(), talent.getId()))
            .collect(Collectors.toList());
    talentDto.setLanguagesList(masterMapper.toLanguageDtoList(languagesTalent));
    talentMasterRepository.getMasterDataOfTalents().stream()
            .filter(talentMasterDataResponse -> Objects.equals(talent.getId(), talentMasterDataResponse.getTalentId()))
            .findFirst()
            .ifPresent(talentMasterDataResponse -> {
              talentDto.setCountryId(talentMasterDataResponse.getCountryId());
              talentDto.setCityId(talentMasterDataResponse.getCityId());
              talentDto.setCurrencyId(talentMasterDataResponse.getCurrencyId());
              talentDto.setProfileId(talentMasterDataResponse.getProfileId());
              talentDto.setCountry(talentMasterDataResponse.getCountry());
              talentDto.setCity(talentMasterDataResponse.getCity());
              talentDto.setCurrency(talentMasterDataResponse.getCurrency());
              talentDto.setProfile(talentMasterDataResponse.getProfile());
            });
    return talentDto;
  }

}
