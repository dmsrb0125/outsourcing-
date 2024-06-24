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

class LoginRequestDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private LoginRequestDto createLoginRequestDto() {
        return new LoginRequestDto("testUser", "testPassword");
    }

    @Test
    @DisplayName("LoginRequestDto 생성 테스트")
    void testLoginRequestDtoCreation() {
        // given
        LoginRequestDto dto = createLoginRequestDto();

        // when & then
        assertThat(dto.getUsername()).isEqualTo("testUser");
        assertThat(dto.getPassword()).isEqualTo("testPassword");
    }

    @Test
    @DisplayName("LoginRequestDto 필드 username 유효성 검사 테스트")
    void testUsernameFieldConstraints() {
        // given
        LoginRequestDto dto = createLoginRequestDto();
        dto.setUsername("");

        // when
        Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations.size()).isEqualTo(1);

        ConstraintViolation<LoginRequestDto> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
        assertThat(violation.getMessage()).isEqualTo("유저네임은 필수 입력 값입니다.");
    }

    @Test
    @DisplayName("LoginRequestDto 필드 password 유효성 검사 테스트")
    void testPasswordFieldConstraints() {
        // given
        LoginRequestDto dto = createLoginRequestDto();
        dto.setPassword("");

        // when
        Set<ConstraintViolation<LoginRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations.size()).isEqualTo(1);

        ConstraintViolation<LoginRequestDto> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("password");
        assertThat(violation.getMessage()).isEqualTo("비밀번호는 필수 입력 값입니다.");
    }

    @Test
    @DisplayName("LoginRequestDto Getter 메서드 테스트")
    void testGetters() {
        // given
        LoginRequestDto dto = createLoginRequestDto();

        // when & then
        assertThat(dto.getUsername()).isEqualTo("testUser");
        assertThat(dto.getPassword()).isEqualTo("testPassword");
    }
}
