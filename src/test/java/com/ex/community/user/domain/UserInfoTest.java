package com.ex.community.user.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInfoTest {
    @Test
    @DisplayName("예외를 던지는지 확인")
    void givenNameAndProfileImage_whenCreated_thenThrowNothing(){

        // given
        String name = "abcd";
        String profileImageUrl = "";

        // when
        // then
        Assertions.assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
    }

    @Test
    @DisplayName("name 유효성, 빈값일때 익셉션 처리")
    void givenBlankNameANdProfileImage_whenCreated_thenThrowError(){

        // given
        String name = "";
        String profileImageUrl = "";

        // when
        // then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new UserInfo(name, profileImageUrl));
    }



}