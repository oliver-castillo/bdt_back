package com.app.bdt.controller;

import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.*;
import com.app.bdt.model.response.TalentCardResponse;
import com.app.bdt.service.ITalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/talent")
@RequiredArgsConstructor
public class TalentController {

  private final ITalentService talentService;

  @GetMapping
  public ResponseEntity<List<TalentDto>> getTalents() {
    return ResponseEntity.ok(talentService.getTalents());
  }

  @GetMapping(value = "/{talentId}")
  public ResponseEntity<Object> getTalentById(@PathVariable Long talentId) {
    Optional<TalentDto> talentDto = talentService.getTalentDtoById(talentId);
    if (!talentDto.isPresent()) {
      throw new NotFoundException("No se encontró el registro");
    }
    return new ResponseEntity<>(talentDto, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<TalentDto> createTalent(@RequestBody @Valid TalentRequest talentRequest) {
    return new ResponseEntity<>(talentService.createTalent(talentRequest), HttpStatus.CREATED);
  }

  @PostMapping("/filter_talents")
  public ResponseEntity<Object> getTalentsByTechnicalSkillsLanguageAndLevel(@RequestBody(required = false) Map<String, Object> params) {
    List<TalentCardResponse> talentByTechnicalSkillsLanguageAndLevel = talentService.getTalentsByTechnicalSkillsLanguageAndLevel(params);
    return new ResponseEntity<>(talentByTechnicalSkillsLanguageAndLevel, HttpStatus.OK);

  }

  @PutMapping("/update_salary_band/{talentId}")
  public ResponseEntity<Object> updateTalentById(@PathVariable Long talentId, @RequestBody @Valid TalentRequest talentRequest) {
    return new ResponseEntity<>(talentService.updateSalaryBand(talentId, talentRequest), HttpStatus.OK);
  }

  @PutMapping("/update_description/{talentId}")
  public ResponseEntity<Object> updateDescription(@PathVariable Long talentId, @RequestBody @Valid Map<String, String> param) {
    return new ResponseEntity<>(talentService.updateDescription(talentId, param.get("description")), HttpStatus.OK);
  }

  @PostMapping("/add_technical_skill/{talentId}")
  public ResponseEntity<Object> addTechnicalSkill(
          @PathVariable Long talentId,
          @RequestBody @Valid TechnicalSkillRequest technicalSkillRequest) {
    return new ResponseEntity<>(talentService.addTechnicalSkill(talentId, technicalSkillRequest), HttpStatus.OK);
  }

  @PostMapping("/add_soft_skill/{talentId}")
  public ResponseEntity<Object> addSoftSkill(
          @PathVariable Long talentId,
          @RequestBody @Valid SoftSkillRequest softSkillRequest) {
    return new ResponseEntity<>(talentService.addSoftSkill(talentId, softSkillRequest), HttpStatus.OK);
  }

  @PostMapping("/add_work_exp/{talentId}")
  public ResponseEntity<Object> addWorkExperience(
          @PathVariable("talentId") Long talentId,
          @RequestBody @Valid WorkExperienceRequest workExperienceRequest) {
    return new ResponseEntity<>(talentService.addWorkExperience(talentId, workExperienceRequest), HttpStatus.OK);
  }

  @PostMapping("/add_edu_exp/{talentId}")
  public ResponseEntity<Object> addEducationalExperience(
          @PathVariable("talentId") Long talentId,
          @RequestBody @Valid EducationalExperienceRequest educationalExperienceRequest) {
    return new ResponseEntity<>(talentService.addEducationalExperience(talentId, educationalExperienceRequest), HttpStatus.OK);
  }

  @PostMapping("/{talentId}/update_work_exp/{workExpId}")
  public ResponseEntity<Object> updateWorkExperience(
          @PathVariable("talentId") Long talentId,
          @PathVariable("workExpId") Long workExpId,
          @RequestBody @Valid WorkExperienceRequest workExperienceRequest) {
    return new ResponseEntity<>(talentService.updateWorkExperience(talentId, workExpId, workExperienceRequest), HttpStatus.OK);
  }

  @PostMapping("/{talentId}/update_edu_exp/{eduExpId}")
  public ResponseEntity<Object> updateEducationalExperience(
          @PathVariable("talentId") Long talentId,
          @PathVariable("eduExpId") Long eduExpId,
          @RequestBody @Valid EducationalExperienceRequest educationalExperienceRequest) {
    return new ResponseEntity<>(talentService.updateEducationalExperience(talentId, eduExpId, educationalExperienceRequest), HttpStatus.OK);
  }

}
