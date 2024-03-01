package com.app.bdt.controller;

import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.TalentRequest;
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
    Optional<TalentDto> talentDto = talentService.getTalentById(talentId);
    if (!talentDto.isPresent()) {
      throw new NotFoundException("No se encontró el registro");
    }
    return new ResponseEntity<>(talentDto, HttpStatus.OK);
  }

  @PostMapping
  @ResponseBody
  public ResponseEntity<TalentDto> createTalent(@RequestBody @Valid TalentRequest talentRequest) {
    return new ResponseEntity<>(talentService.createTalent(talentRequest), HttpStatus.CREATED);
  }

  @PostMapping("/filter_talents")
  public ResponseEntity<Object> getTalentsByTechnicalSkillsLanguageAndLevel(@RequestBody(required = false) Map<String, Object> params) {
    List<Map<String, Object>> talentByTechnicalSkillsLanguageAndLevel = talentService.getTalentsByTechnicalSkillsLanguageAndLevel(params);
    if (!talentByTechnicalSkillsLanguageAndLevel.isEmpty()) {
      return new ResponseEntity<>(talentByTechnicalSkillsLanguageAndLevel, HttpStatus.OK);
    }
    throw new NotFoundException("No se encontraron registros");
  }

  @PutMapping("/update_salary_band/{talentId}")
  public ResponseEntity<Object> updateTalentById(@PathVariable Long talentId, @RequestBody @Valid TalentRequest talentRequest) {
    return new ResponseEntity<>(talentService.updateSalaryBandOfTalent(talentId, talentRequest), HttpStatus.OK);
  }

}
