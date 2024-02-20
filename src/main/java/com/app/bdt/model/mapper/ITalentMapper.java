package com.app.bdt.model.mapper;

import com.app.bdt.model.dto.SoftSkillDto;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.dto.TechnicallSkillDto;
import com.app.bdt.model.entity.SoftSkill;
import com.app.bdt.model.entity.Talent;
import com.app.bdt.model.entity.TechnicalSkills;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITalentMapper {

  ITalentMapper INSTANCE = Mappers.getMapper(ITalentMapper.class);

  @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToString")
  @Mapping(target = "softSkillList", source = "softSkillList")
  @Mapping(target = "technicalSkillList", source = "technicalSkillList")
  TalentDto toTalentDto(Talent talent);

  @Mapping(target = "image", source = "image", qualifiedByName = "stringToByteArray")
  @Mapping(target = "softSkillList", source = "softSkillList")
  @Mapping(target = "technicalSkillList", source = "technicalSkillList")
  Talent toTalent(TalentDto talentDto);

  List<TalentDto> toTalentDtoList(List<Talent> talents);
  SoftSkillDto toSoftSkillDto(SoftSkill softSkill);

  SoftSkill toSoftSkill(SoftSkillDto softSkillDto);

  TechnicallSkillDto toTechnicalSkillDto(TechnicalSkills technicalSkill);

  TechnicalSkills toTechnicalSkill(TechnicallSkillDto technicalSkillDto);

  @Named("byteArrayToString")
  default String byteArrayToString(byte[] byteArray) {
    return byteArray != null ? new String(byteArray) : null;
  }

  @Named("stringToByteArray")
  default byte[] stringToByteArray(String string) {
    return string != null ? string.getBytes() : null;
  }
}
