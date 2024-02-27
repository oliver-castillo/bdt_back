package com.app.bdt.controller;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.service.ITalentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

}
