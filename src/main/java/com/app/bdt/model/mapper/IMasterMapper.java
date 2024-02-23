package com.app.bdt.model.mapper;

import com.app.bdt.model.dto.LanguageDto;
import com.app.bdt.model.response.ILanguagesTalent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMasterMapper {

  IMasterMapper INSTANCE = Mappers.getMapper(IMasterMapper.class);

  List<LanguageDto> toLanguageDtoList(List<ILanguagesTalent> list);

}
