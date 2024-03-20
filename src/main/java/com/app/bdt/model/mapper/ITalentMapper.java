package com.app.bdt.model.mapper;

import com.app.bdt.model.dto.FileDto;
import com.app.bdt.model.dto.TalentDto;
import com.app.bdt.model.dto.UserBasicDto;
import com.app.bdt.model.entity.*;
import com.app.bdt.model.request.*;
import com.app.bdt.model.response.TalentCardResponse;
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
  @Mapping(target = "filesList", source = "filesList", qualifiedByName = "filesList")
  Talent toTalent(TalentRequest TalentRequest);

  @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToString")
  @Mapping(target = "filesList", source = "filesList", qualifiedByName = "filesDtoList")
  TalentDto toTalentDto(Talent talent);

  @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToString")
  UserBasicDto userToUserBasicDto(User user);

  WorkExperience toWorkExperience(WorkExperienceRequest workExperienceRequest);

  EducationalExperience toEducationalExperience(EducationalExperienceRequest educationalExperienceRequest);

  List<TalentDto> toTalentDtoList(List<Talent> talents);

  List<TalentCardResponse> toTalentCardResponseList(List<TalentDto> talentDto);

  Language toLanguage(LanguageRequest languageRequest);

  @Mapping(target = "fileInBytes", source = "file", qualifiedByName = "stringToByteArray")
  File toFile(FileRequest fileRequest);

  @Mapping(target = "file", source = "fileInBytes", qualifiedByName = "byteArrayToString")
  FileDto toFileDto(File file);

  @Named("filesList")
  List<File> toFilesList(List<FileRequest> filesRequest);

  @Named("filesDtoList")
  List<FileDto> toFilesDtoList(List<File> files);

  @Named("byteArrayToString")
  default String byteArrayToString(byte[] byteArray) {
    byte[] decodedImage = Base64.getDecoder().decode(byteArray);
    return byteArray != null ? new String(decodedImage) : null;
  }

  @Named("stringToByteArray")
  default byte[] stringToByteArray(String file) {
    file = file.contains(",") ? file.split(",")[1] : file;
    byte[] base64Image = Base64.getEncoder().encode(file.getBytes());
    return file != null ? base64Image : null;
  }

}
