package com.sparta.redirect_outsourcing.domain.user.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ProfileResponseDtoTest {

    @Test
    @DisplayName("ProfileResponseDto 생성 테스트")
    void testProfileResponseDtoCreation() {
        // given
        String nickname = "nickname";
        String introduce = "Introduce myself";
        String pictureUrl = "http://example.com/picture.jpg";

        // when
        ProfileResponseDto dto = new ProfileResponseDto(nickname, introduce, pictureUrl);

        // then
        assertThat(dto.getNickname()).isEqualTo(nickname);
        assertThat(dto.getIntroduce()).isEqualTo(introduce);
        assertThat(dto.getPictureUrl()).isEqualTo(pictureUrl);
    }

    @Test
    @DisplayName("Getter 메서드 테스트")
    void testGetters() {
        // given
        String nickname = "nickname";
        String introduce = "Introduce myself";
        String pictureUrl = "http://example.com/picture.jpg";
        ProfileResponseDto dto = new ProfileResponseDto(nickname, introduce, pictureUrl);

        // when & then
        assertThat(dto.getNickname()).isEqualTo(nickname);
        assertThat(dto.getIntroduce()).isEqualTo(introduce);
        assertThat(dto.getPictureUrl()).isEqualTo(pictureUrl);
    }
}
