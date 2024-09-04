package com.ex.community.user.application;

import com.ex.community.user.application.dto.CreateUserRequestDto;
import com.ex.community.user.application.interfaces.UserRepository;
import com.ex.community.user.domain.User;
import com.ex.community.user.domain.UserInfo;
import com.ex.community.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private final FakeUserRepository fakeUserRepository = new FakeUserRepository();
    private final UserService userService = new UserService(fakeUserRepository);

    @Test
    void givenUserInfo_whenCreateUser_thenCanFindUser(){

        // given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        // when
        User savedUser = userService.createUser(dto);

        // then
        User findUser = userService.getUser(savedUser.getId());
        UserInfo userInfo = findUser.getInfo();

        Assertions.assertEquals(savedUser.getId(), findUser.getId());
        Assertions.assertEquals("test", userInfo.getName());
    }
}