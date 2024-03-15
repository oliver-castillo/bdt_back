package com.app.bdt.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.entity.User;
import com.app.bdt.model.request.UserRequest;

@Mapper(componentModel = "spring")
public interface IUserMapper {

  IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

  @Mapping(target = "image", source = "image", qualifiedByName = "byteArrayToString")
  //@Mapping(target = "roles", source = "roles", qualifiedByName = "stringListToRoleDtoList")
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

  /*@Named("stringListToRoleDtoList")
  default List<RoleDto> stringListToRoleDtoList(List<String> roles) {
    // Aquí debes implementar la lógica para convertir una lista de cadenas a una lista de RoleDto.
    // Esto dependerá de cómo esté definida tu clase RoleDto.
    // Este es solo un ejemplo y necesitarás adaptarlo a tus necesidades.
    return roles.stream()
            .map(role -> new RoleDto(role)) // Asume que RoleDto tiene un constructor que acepta una cadena.
            .collect(Collectors.toList());
  }*/
}
