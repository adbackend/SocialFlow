package com.ex.community.user.application;

import com.ex.community.user.application.dto.CreateUserRequestDto;
import com.ex.community.user.application.dto.FollowUserRequestDto;
import com.ex.community.user.application.interfaces.UserRelationRepository;
import com.ex.community.user.application.interfaces.UserRepository;
import com.ex.community.user.domain.User;
import com.ex.community.user.repository.FakeUserRelationRepository;
import com.ex.community.user.repository.FakeUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserRelationServiceTest {

    private final UserRepository userRepository = new FakeUserRepository();
    private final UserService userService = new UserService(userRepository);
    private final UserRelationRepository userRelationRepository = new FakeUserRelationRepository();
    private final UserRelationService userRelationService = new UserRelationService(userService, userRelationRepository);

    private User user1;
    private User user2;
    private FollowUserRequestDto followUserRequestDto;

    @BeforeEach
    void init(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
        this.user1 = userService.createUser(dto);
        this.user2 = userService.createUser(dto);

        this.followUserRequestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
    }

    @Test
    @DisplayName("팔로우 처리 확인")
    void givenCreateTwoUser_whenFollow_thenUserFollowSaved(){

        // when
        userRelationService.follow(followUserRequestDto);

        // then
        Assertions.assertEquals(1, user1.followingCount());
        Assertions.assertEquals(1, user2.followerCount());

    }

    @Test
    @DisplayName("언팔로우 처리 확인")
    void givenCreateTwoUserFollow_whenUnfollow_thenUserFollowSaved(){

        // given
        userRelationService.follow(followUserRequestDto);

        // when
        userRelationService.unfollow(followUserRequestDto);

        // then
        Assertions.assertEquals(0, user1.followingCount());
        Assertions.assertEquals(0, user2.followerCount());
    }

    @Test
    @DisplayName("언팔로우 예외 처리 확인")
    void givenCreateTwoUser_whenUnfollow_thenUserThrowError(){

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, ()->userRelationService.unfollow(followUserRequestDto));
    }

    @Test
    @DisplayName("이미 팔로우 상태인데 또 팔로우 한 경우")
    void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError(){

        // given
        userRelationService.follow(followUserRequestDto);

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, ()->userRelationService.follow(followUserRequestDto));
    }

    @Test
    @DisplayName("자신이 자신을 팔로우 했을때 오류 처리 확인")
    void givenCreateOneUser_whenFollow_thenUserThrowError(){

        // given
        FollowUserRequestDto sameUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class,()->userRelationService.follow(sameUser));
        
    }

}