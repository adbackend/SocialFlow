package com.ex.community.user.application;

import com.ex.community.user.application.dto.CreateUserRequestDto;
import com.ex.community.user.application.interfaces.UserRepository;
import com.ex.community.user.domain.User;
import com.ex.community.user.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto){

        UserInfo userInfo = new UserInfo(dto.name(), dto.profileImageUrl());
        User user = new User(null, userInfo);
        return userRepository.save(user);
    }

    public User getUser(Long id){
       return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
