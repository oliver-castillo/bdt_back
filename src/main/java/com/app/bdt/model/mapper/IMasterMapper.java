package com.app.bdt.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.app.bdt.model.dto.LanguageDto;
import com.app.bdt.model.response.ILanguagesTalent;

@Mapper(componentModel = "spring")
public interface IMasterMapper {

  IMasterMapper INSTANCE = Mappers.getMapper(IMasterMapper.class);

  List<LanguageDto> toLanguageDtoList(List<ILanguagesTalent> list);

}
