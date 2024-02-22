package com.app.bdt.service.serviceImpl;


import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.mapper.ITalentMapper;
import com.app.bdt.model.request.TalentRequest;
import com.app.bdt.repository.ITalentRepository;
import com.app.bdt.service.ITalentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class TalentService implements ITalentService {

  private final ITalentRepository talentRepository;
  private final ITalentMapper talentMapper;

  Logger log = Logger.getLogger(this.getClass().getName());

  public TalentService(ITalentRepository talentRepository, ITalentMapper talentMapper) {
    this.talentRepository = talentRepository;
    this.talentMapper = talentMapper;
  }

  @Override
  public List<TalentDto> getTalents() {
    try {
      return talentMapper.toTalentDtoList(talentRepository.findAllTalents());
    } catch (RuntimeException e) {
      log.warning("EXCEPTION HERE: " + e.getMessage());
      throw new RuntimeException();
    }
  }

  @Override
  public TalentDto createTalent(TalentRequest talentRequest) {
    try {
      talentRepository.createTalent(talentMapper.toTalent(talentRequest));
      return talentMapper.toTalentDto(talentRepository.getLastInsertedTalent());
    } catch (RuntimeException e) {
      throw new RuntimeException();
    }
  }

}
