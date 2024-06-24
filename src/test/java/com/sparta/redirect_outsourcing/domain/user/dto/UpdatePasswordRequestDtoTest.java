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

class UpdatePasswordRequestDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private UpdatePasswordRequestDto createUpdatePasswordRequestDto() {
        UpdatePasswordRequestDto dto = new UpdatePasswordRequestDto();
        dto.setCurrentPassword("CurrentPassword1!");
        dto.setNewPassword("NewPassword1!");
        return dto;
    }

    @Test
    @DisplayName("UpdatePasswordRequestDto 생성 테스트")
    void testUpdatePasswordRequestDtoCreation() {
        // given
        UpdatePasswordRequestDto dto = createUpdatePasswordRequestDto();

        // when & then
        assertThat(dto.getCurrentPassword()).isEqualTo("CurrentPassword1!");
        assertThat(dto.getNewPassword()).isEqualTo("NewPassword1!");
    }

    @Test
    @DisplayName("UpdatePasswordRequestDto 필드 currentPassword 유효성 검사 테스트 - NotBlank")
    void testCurrentPasswordFieldNotBlank() {
        // given
        UpdatePasswordRequestDto dto = createUpdatePasswordRequestDto();
        dto.setCurrentPassword(null); // @NotBlank 검사를 유발

        // when
        Set<ConstraintViolation<UpdatePasswordRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("현재 비밀번호는 필수 입력 값입니다.");
    }

    @Test
    @DisplayName("UpdatePasswordRequestDto 필드 newPassword 유효성 검사 테스트 - NotBlank")
    void testNewPasswordFieldNotBlank() {
        // given
        UpdatePasswordRequestDto dto = createUpdatePasswordRequestDto();
        dto.setNewPassword(null); // @NotBlank 검사를 유발

        // when
        Set<ConstraintViolation<UpdatePasswordRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("비밀번호는 필수 입력 값입니다.");
    }

    @Test
    @DisplayName("UpdatePasswordRequestDto 필드 newPassword 유효성 검사 테스트 - Size")
    void testNewPasswordFieldSize() {
        // given
        UpdatePasswordRequestDto dto = createUpdatePasswordRequestDto();
        dto.setNewPassword("Short1!"); // @Size 검사를 유발

        // when
        Set<ConstraintViolation<UpdatePasswordRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("비밀번호는 8자 이상, 15자 이하이어야 합니다.");
    }

    @Test
    @DisplayName("UpdatePasswordRequestDto 필드 newPassword 유효성 검사 테스트 - Pattern")
    void testNewPasswordFieldPattern() {
        // given
        UpdatePasswordRequestDto dto = createUpdatePasswordRequestDto();
        dto.setNewPassword("password"); // @Pattern 검사를 유발

        // when
        Set<ConstraintViolation<UpdatePasswordRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("비밀번호는 최소 하나의 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.");
    }

    @Test
    @DisplayName("UpdatePasswordRequestDto Getter 메서드 테스트")
    void testGetters() {
        // given
        UpdatePasswordRequestDto dto = createUpdatePasswordRequestDto();

        // when & then
        assertThat(dto.getCurrentPassword()).isEqualTo("CurrentPassword1!");
        assertThat(dto.getNewPassword()).isEqualTo("NewPassword1!");
    }
}
