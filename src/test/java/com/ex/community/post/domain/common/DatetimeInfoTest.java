package com.ex.community.post.domain.common;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DatetimeInfoTest {

    @Test
    void givenCreated_whenUpdated_thenTimeAndStatedArsUpdated(){

        // given
        DatetimeInfo datetimeInfo = new DatetimeInfo();
        LocalDateTime localDateTime = datetimeInfo.getDateTime();

        // when
        datetimeInfo.updateEditDatetime();

        //then
        Assertions.assertTrue(datetimeInfo.isEditor());
        Assertions.assertEquals(localDateTime, datetimeInfo.getDateTime());
    }

}