package com.app.bdt.service.serviceImpl;

import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.exceptions.NotFoundException;
import com.app.bdt.model.dto.ListUserDto;
import com.app.bdt.model.dto.ListUserTalentDto;
import com.app.bdt.model.dto.RoleDto;
import com.app.bdt.model.dto.UserDto;
import com.app.bdt.model.entity.User;
import com.app.bdt.model.mapper.IUserMapper;
import com.app.bdt.model.request.UserListRequest;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.model.request.UserTalentListRequest;
import com.app.bdt.model.response.IListUserTalentResponse;
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
import java.util.*;
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
  public UserDto getUserByUserId(Long userId) {
    try {
      return userMapper.toUserDto(userRepository.findById(userId)
              .orElseThrow(() -> new NotFoundException(Messages.NOT_FOUND.getMessage())));
    } catch (NotFoundException e) {
      throw e;
    } catch (RuntimeException e) {
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
  public ListUserDto getListsByUserId(Long userId) {
    try {
      List<IListUserTalentResponse> listUserTalentResponses = userRepository.findListsByUserId(userId);
      ListUserDto listUserDto = new ListUserDto();
      listUserDto.setUserId(userId);
      List<ListUserTalentDto> listUserTalentDtos = new ArrayList<>();

      Map<Long, ListUserTalentDto> listMap = new HashMap<>();
      for (IListUserTalentResponse response : listUserTalentResponses) {
        Long listId = response.getListId();
        String listName = response.getListName();
        Long talentId = response.getTalentId();

        ListUserTalentDto talentDto = listMap.getOrDefault(listId, new ListUserTalentDto());
        talentDto.setId(listId);
        talentDto.setName(listName);

        Set<Long> talentIds = talentDto.getTalentIds();
        if (talentIds == null) {
          talentIds = new HashSet<>();
        }
        talentIds.add(talentId);
        talentDto.setTalentIds(talentIds);

        listMap.put(listId, talentDto);
      }
      listUserTalentDtos.addAll(listMap.values());
      listUserDto.setLists(listUserTalentDtos);
      return listUserDto;
    } catch (RuntimeException e) {
      throw new InternalServerError(e.getMessage());
    }
  }

}
