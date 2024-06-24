package com.sparta.redirect_outsourcing.domain.user.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateProfileRequestDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private UpdateProfileRequestDto createUpdateProfileRequestDto() {
        UpdateProfileRequestDto dto = new UpdateProfileRequestDto();
        dto.setNickname("nickname");
        dto.setIntroduce("Introduce myself");
        return dto;
    }

    @Test
    @DisplayName("UpdateProfileRequestDto 생성 테스트")
    void testUpdateProfileRequestDtoCreation() {
        // given
        UpdateProfileRequestDto dto = createUpdateProfileRequestDto();

        // when & then
        assertThat(dto.getNickname()).isEqualTo("nickname");
        assertThat(dto.getIntroduce()).isEqualTo("Introduce myself");
    }

    @Test
    @DisplayName("UpdateProfileRequestDto 필드 nickname 유효성 검사 테스트 - Size")
    void testNicknameFieldSize() {
        // given
        UpdateProfileRequestDto dto = createUpdateProfileRequestDto();
        dto.setNickname("ThisNicknameIsWayTooLong");

        // when
        Set<ConstraintViolation<UpdateProfileRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<UpdateProfileRequestDto> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("nickname");
        assertThat(violation.getMessage()).isEqualTo("이름은 최대 10글자까지 입력 가능합니다.");
    }

    @Test
    @DisplayName("UpdateProfileRequestDto 필드 introduce 유효성 검사 테스트 - Size")
    void testIntroduceFieldSize() {
        // given
        UpdateProfileRequestDto dto = createUpdateProfileRequestDto();
        dto.setIntroduce("This introduction is way too long to fit the allowed limit.");

        // when
        Set<ConstraintViolation<UpdateProfileRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        ConstraintViolation<UpdateProfileRequestDto> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("introduce");
        assertThat(violation.getMessage()).isEqualTo("한줄소개는 최대 20글자까지 입력 가능합니다.");
    }

    @Test
    @DisplayName("UpdateProfileRequestDto Getter 메서드 테스트")
    void testGetters() {
        // given
        UpdateProfileRequestDto dto = createUpdateProfileRequestDto();

        // when & then
        assertThat(dto.getNickname()).isEqualTo("nickname");
        assertThat(dto.getIntroduce()).isEqualTo("Introduce myself");
    }
}
