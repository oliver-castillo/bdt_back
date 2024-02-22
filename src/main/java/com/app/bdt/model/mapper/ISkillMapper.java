package com.app.bdt.model.mapper;

import com.app.bdt.model.dto.SoftSkillDto;
import com.app.bdt.model.dto.TechnicallSkillDto;
import com.app.bdt.model.entity.SoftSkill;
import com.app.bdt.model.entity.TechnicalSkill;
import com.app.bdt.model.request.SoftSkillRequest;
import com.app.bdt.model.request.TechnicalSkillRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ISkillMapper {

  ISkillMapper INSTANCE = Mappers.getMapper(ISkillMapper.class);

  /*Technical Skills*/

  TechnicalSkill toTechnicalSkill(TechnicalSkillRequest technicalSkillRequest);

  TechnicallSkillDto toTechnicallSkillDto(TechnicalSkill technicalSkill);

  List<TechnicallSkillDto> toTechnicalSkillsDtoList(List<TechnicalSkill> technicalSkillsList);

  /*Soft Skills*/

  SoftSkill toSoftSkill(SoftSkillRequest softSkillRequest);

  SoftSkillDto toSoftSkillDto(SoftSkill softSkill);

  List<SoftSkillDto> toSoftSkillsDtoList(List<SoftSkill> softSkillsList);


}
