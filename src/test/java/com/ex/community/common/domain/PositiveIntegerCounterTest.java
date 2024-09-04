package com.ex.community.common.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  given - 주어진 상태
 *  when - 조건이 가해진 상태
 *  then - 결과
 * */
class PositiveIntegerCounterTest {

    @Test
    @DisplayName("카운트 +1 증가")
    void givenCreated_whenIncrease_thenCountIsOne(){

        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.increase();

        // then
        Assertions.assertEquals(1, counter.getCount());
    }

    @Test
    @DisplayName("카운터 -1 감소")
    void givenCreatedAndIncreased_whenDecrease_thenCountIsZero(){

        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();
        counter.increase();
        
        // when
        counter.decrease();

        // then
        Assertions.assertEquals(0, counter.getCount());
    }
    
    @Test
    @DisplayName("0이하 일때 감소가 안되는지 ")
    void givenCreated_whenDecrease_thenCountIsZero(){

        // given
        PositiveIntegerCounter counter = new PositiveIntegerCounter();

        // when
        counter.decrease();

        // then
        Assertions.assertEquals(0, counter.getCount());
    }


}