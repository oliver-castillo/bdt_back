package com.app.bdt.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.Talent;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ITalentMapper {

  ITalentMapper INSTANCE = Mappers.getMapper(ITalentMapper.class);

  @Mapping(target = "imagen", source = "imagen", qualifiedByName = "byteArrayToString")
  TalentDto toTalentDto(Talent talent);

  @Mapping(target = "imagen", source = "imagen", qualifiedByName = "stringToByteArray")
  Talent toTalent(TalentDto talentDto);

  List<TalentDto> toTalentDtoList(List<Talent> talents);

  @Named("byteArrayToString")
  default String byteArrayToString(byte[] byteArray) {
    return byteArray != null ? new String(byteArray) : null;
  }

  @Named("stringToByteArray")
  default byte[] stringToByteArray(String string) {
    return string != null ? string.getBytes() : null;
  }
}
