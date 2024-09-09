package com.ex.community.post.domain.content;

import com.ex.community.post.domain.Post;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent(){

        // given
        String text = "this is a test";

        // when
        PostContent postContent = new PostContent(text);

        // then
        Assertions.assertEquals(text, postContent.contentText);
    }

    @Test
    void givenContextLengthIsOver_whenCreated_thenThrowError(){

        // given
        String text = "a".repeat(501);

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(text));

    }

    @ParameterizedTest
    @ValueSource(strings = {"뷁, 닭, 굵, 삵, 슲, 끓"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreaWord){

        // given
        String text = koreaWord.repeat(501);

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(text));

    }

    @Test
    void givenContentLengthIsUnder_whenCreated_thenThrowError(){

        // given
        String text = "a".repeat(4);

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(text));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentIsEmpty_whenCreated_thenThrowError(String value){

        // when, then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }

}