package com.ex.community.user.domain;

import net.bytebuddy.agent.builder.AgentBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final UserInfo userInfo = new UserInfo("홍길동", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init(){
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    void givenTwoUser_whenEquals_thenReturnFalse(){

        // when
        boolean isSame = user1.equals(user2);

        // then
        Assertions.assertEquals(false, isSame);
    }

    @Test
    void givenTwoSameIdUser_whenEqual_thenReturnTrue(){

        // given
        User sameUser = new User(1L, userInfo);

        // when
        boolean isSame = user1.equals(sameUser);

        // then
        Assertions.assertTrue(isSame);
    }

    @Test
    void givenTwoUser_whenHashCode_thenReturnFalse(){

        // given
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        // then
        Assertions.assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    void givenTwoSameIdUsers_whenHashCode_thenEqual(){

        // given
        User sameUser = new User(1L, userInfo);

        // when
        int hashCode1 = user1.hashCode();
        int sameUserHashCode = sameUser.hashCode();

        Assertions.assertEquals(hashCode1, sameUserHashCode);
    }

    @Test
    @DisplayName("팔로우 하기")
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount(){

        // when
        user1.follow(user2); // user1 이 user2를 팔로우

        // then
        Assertions.assertEquals(1, user1.followingCount());
        Assertions.assertEquals(0, user1.followerCount());

        Assertions.assertEquals(0, user2.followingCount());
        Assertions.assertEquals(1, user2.followerCount());
    }

    @Test
    @DisplayName("언팔로우")
    void givenTwoUser1FollowUser2_whenUnfollow_thenDecreaseUserCount(){

        // given
        user1.follow(user2);

        // when
        user1.unfollow(user2);

        // then
        Assertions.assertEquals(0, user1.followingCount());
        Assertions.assertEquals(0, user1.followerCount());

        Assertions.assertEquals(0, user2.followingCount());
        Assertions.assertEquals(0, user2.followerCount());

    }

    @Test
    @DisplayName("팔로우를 하지 않았을 때, 0이하기 되는지")
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount(){

        // when
        user1.unfollow(user2);
        // then
        Assertions.assertEquals(0, user1.followingCount());
        Assertions.assertEquals(0, user1.followerCount());

        Assertions.assertEquals(0, user2.followingCount());
        Assertions.assertEquals(0, user2.followerCount());

    }


}