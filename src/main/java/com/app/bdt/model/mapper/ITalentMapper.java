package com.app.bdt.model.mapper;

import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.entity.Talent;
import com.app.bdt.model.request.TalentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Base64;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ITalentMapper {

  ITalentMapper INSTANCE = Mappers.getMapper(ITalentMapper.class);

  @Mapping(target = "image", source = "image", qualifiedByName = "stringToByteArray")
  Talent toTalent(TalentRequest TalentRequest);

  @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToString")
  TalentDto toTalentDto(Talent talent);

  List<TalentDto> toTalentDtoList(List<Talent> talents);

  @Named("byteArrayToString")
  default String byteArrayToString(byte[] byteArray) {
    String decodedImage = Base64.getDecoder().decode(byteArray).toString();
    return byteArray != null ? new String(byteArray) : null;
  }

  @Named("stringToByteArray")
  default byte[] stringToByteArray(String image) {
    image = image.contains(",") ? image.split(",")[1] : image;
    byte[] base64Image = Base64.getEncoder().encode(image.getBytes());
    return image != null ? base64Image : null;
  }

}
