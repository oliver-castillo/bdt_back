package com.app.bdt.service.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.app.bdt.exceptions.InternalServerError;
import com.app.bdt.model.mapper.IUserMapper;
import com.app.bdt.model.request.UserRequest;
import com.app.bdt.repository.IUserRepository;
import com.app.bdt.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

  private final IUserRepository userRepository;
  private final IUserMapper userMapper;

  public UserService(IUserRepository userRepository, IUserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  @Override
  public void createUser(UserRequest userRequest) {
    try {
      userRepository.createUser(userMapper.toUser(userRequest));
    } catch (RuntimeException e) {
      System.out.println(e.getMessage());
      throw new InternalServerError(e.getMessage());
    }
  }
}
