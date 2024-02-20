package com.app.bdt.model.mapper;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.Talent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITalentMapper {

  ITalentMapper INSTANCE = Mappers.getMapper(ITalentMapper.class);

  @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToString")
  TalentDto toTalentDto(Talent talent);

  @Mapping(target = "image", source = "image", qualifiedByName = "stringToByteArray")
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
