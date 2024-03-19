package com.app.bdt.service.serviceImpl;

import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.dto.RoleDto;
import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.entity.User;
import com.app.bdt.model.mapper.IUserMapper;
import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.request.UserTalentListRequest;
import com.app.bdt.model.response.IUserAndRole;
import com.app.bdt.model.response.Response;
import com.app.bdt.repository.IUserRepository;
import com.app.bdt.service.IUserService;
import com.app.bdt.util.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {

  private final IUserRepository userRepository;
  private final IUserMapper userMapper;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public void createUser(UserRequest userRequest) {
    try {
      userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
      userRepository.createUser(userMapper.toUser(userRequest));
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public UserDto getUserByUsername(String username) {
    try {
      Optional<User> optionalUser = userRepository.findUserByUsername(username);
      User user = optionalUser.orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage()));
      List<IUserAndRole> userAndRoles = userRepository.findUsersWithRole().stream()
              .filter(obj -> Objects.equals(obj.getUserId(), user.getId()))
              .collect(Collectors.toList());
      List<RoleDto> roleDtoList = userAndRoles.stream()
              .map(userAndRole -> new RoleDto(userAndRole.getRoleId(), userAndRole.getRoleName()))
              .collect(Collectors.toList());
      UserDto userDto = userMapper.toUserDto(user);
      userDto.setRoles(roleDtoList);
      return userDto;
    } catch (NotFoundException e) {
      throw e;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response addList(UserListRequest userListRequest) {
    try {
      userRepository.addListOfUser(userListRequest);
      return new Response(HttpStatus.CREATED.value(), Messages.SUCCESSFUL_INSERT.getMessage());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Response addListTalent(UserTalentListRequest userTalentListRequest) {
    try {
      userRepository.addListUserTalent(userTalentListRequest);
      return new Response(HttpStatus.CREATED.value(), Messages.SUCCESSFUL_INSERT.getMessage());
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

  @Override
  public Optional<User> getUser(Long userId) {
    return userRepository.findById(userId);
  }

}
