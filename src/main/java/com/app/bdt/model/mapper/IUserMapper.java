package com.app.bdt.model.mapper;

import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.entity.User;
import com.app.bdt.model.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IUserMapper {

  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToString")
  UserDto toUserDto(User user);

  @Mapping(target = "image", source = "image", qualifiedByName = "stringToByteArray")
  User toUser(UserRequest userRequest);

  @Named("byteArrayToString")
  default String byteArrayToString(byte[] byteArray) {
    return byteArray != null ? new String(byteArray) : null;
  }

  @Named("stringToByteArray")
  default byte[] stringToByteArray(String string) {
    return string != null ? string.getBytes() : null;
  }
}
