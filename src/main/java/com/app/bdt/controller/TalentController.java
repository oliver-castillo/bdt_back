package com.app.bdt.controller;

import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.model.response.ITalentByLanguageAndLevel;
import com.app.bdt.service.ITalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/talent")
@RequiredArgsConstructor
public class TalentController {

  private final ITalentService talentService;

  @GetMapping
  public ResponseEntity<List<TalentDto>> getTalents() {
    return ResponseEntity.ok(talentService.getTalents());
  }

  @PostMapping
  public ResponseEntity<TalentDto> createTalent(@RequestBody @Valid TalentRequest talentRequest) {
    return new ResponseEntity<>(talentService.createTalent(talentRequest), HttpStatus.CREATED);
  }

  @GetMapping("/filter_by_technical_skills")
  public ResponseEntity<Object> getTalentsByTechnicalSkills(@RequestParam List<String> technicalSkills) {
    List<Map<String, Object>> talentByTechnicalSkills = talentService.getTalentsByTechnicalSkills(technicalSkills);
    if (!talentByTechnicalSkills.isEmpty()) {
      return new ResponseEntity<>(talentByTechnicalSkills, HttpStatus.OK);
    }
    throw new NotFoundException("No se encontraron registros");
  }

  @GetMapping("/filter_by_language_and_level")
  public ResponseEntity<Object> getTalentsByLanguageAndLevel(@RequestParam int languageId, @RequestParam int levelId) {
    List<ITalentByLanguageAndLevel> talentByLanguageAndLevel = talentService.getTalentsByLanguageAndLevel(languageId, levelId);
    if (!talentByLanguageAndLevel.isEmpty()) {
      return new ResponseEntity<>(talentByLanguageAndLevel, HttpStatus.OK);
    }
    throw new NotFoundException("No se encontraron registros");
  }

}
