package com.app.bdt.controller;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.service.serviceImpl.TalentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/talent")
@CrossOrigin(origins = "http://localhost:4200")
public class TalentController {


  private final TalentService talentService;

  public TalentController(TalentService talentService) {
    this.talentService = talentService;
  }

  @GetMapping
  public ResponseEntity<List<TalentDto>> getTalents() {
    return ResponseEntity.ok(talentService.getTalents());
  }

  @PostMapping
  public ResponseEntity<Object> createTalent(@RequestBody TalentRequest talentRequest) {
    return new ResponseEntity<>(talentService.createTalent(talentRequest), HttpStatus.CREATED);
  }

}
