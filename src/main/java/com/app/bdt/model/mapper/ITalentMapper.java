package com.app.bdt.model.mapper;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.Talent;
import com.app.bdt.model.request.TalentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITalentMapper {

  ITalentMapper INSTANCE = Mappers.getMapper(ITalentMapper.class);

  /* @Mapping(target = "softSkillList", source = "softSkillList")
  @Mapping(target = "technicalSkillList", source = "technicalSkillList")
  @Mapping(target = "workExperienceList", source = "workExperienceList")
  @Mapping(target = "educationalExperienceList", source = "educationalExperienceList")*/
  @Mapping(target = "image", source = "image", qualifiedByName = "stringToByteArray")
  Talent toTalent(TalentRequest TalentRequest);

  /* @Mapping(target = "softSkillList", source = "softSkillList")
  @Mapping(target = "technicalSkillList", source = "technicalSkillList")
  @Mapping(target = "workExperienceList", source = "workExperienceList")
  @Mapping(target = "educationalExperienceList", source = "educationalExperienceList")*/
  @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToString")
  TalentDto toTalentDto(Talent talent);

  List<TalentDto> toTalentDtoList(List<Talent> talents);

  @Named("byteArrayToString")
  default String byteArrayToString(byte[] byteArray) {
    return byteArray != null ? new String(byteArray) : null;
  }

  @Named("stringToByteArray")
  default byte[] stringToByteArray(String image) {
    image = image.contains(",") ? image.split(",")[1] : image;
    return image != null ? image.getBytes() : null;
  }

}
