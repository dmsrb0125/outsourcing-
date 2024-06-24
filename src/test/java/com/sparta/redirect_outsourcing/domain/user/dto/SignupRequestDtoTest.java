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

class SignupRequestDtoTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private SignupRequestDto createSignupRequestDto() {
        SignupRequestDto dto = new SignupRequestDto();
        dto.setUsername("testuser");
        dto.setPassword("Password1!");
        return dto;
    }

    private SignupRequestDto createAdminSignupRequestDto() {
        SignupRequestDto dto = new SignupRequestDto();
        dto.setUsername("adminuser");
        dto.setPassword("AdminPassword1!");
        dto.setAdmin(true);
        dto.setAdminPassword("1234");
        return dto;
    }

    @Test
    @DisplayName("SignupRequestDto 일반 회원가입 생성 테스트")
    void testSignupRequestDtoCreation() {
        // given
        SignupRequestDto dto = createSignupRequestDto();

        // when & then
        assertThat(dto.getUsername()).isEqualTo("testuser");
        assertThat(dto.getPassword()).isEqualTo("Password1!");
        assertThat(dto.isAdmin()).isFalse();
        assertThat(dto.getAdminPassword()).isEqualTo("");
    }

    @Test
    @DisplayName("SignupRequestDto 관리자 회원가입 생성 테스트")
    void testAdminSignupRequestDtoCreation() {
        // given
        SignupRequestDto dto = createAdminSignupRequestDto();

        // when & then
        assertThat(dto.getUsername()).isEqualTo("adminuser");
        assertThat(dto.getPassword()).isEqualTo("AdminPassword1!");
        assertThat(dto.isAdmin()).isTrue();
        assertThat(dto.getAdminPassword()).isEqualTo("1234");
    }

    @Test
    @DisplayName("SignupRequestDto 필드 username 유효성 검사 테스트 - NotBlank")
    void testUsernameFieldNotBlank() {
        // given
        SignupRequestDto dto = createSignupRequestDto();
        dto.setUsername(null); // @NotBlank 검사를 유발

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("유저네임은 필수 입력 값입니다.");
    }

    @Test
    @DisplayName("SignupRequestDto 필드 username 유효성 검사 테스트 - Size")
    void testUsernameFieldSize() {
        // given
        SignupRequestDto dto = createSignupRequestDto();
        dto.setUsername("usr"); // @Size 검사를 유발

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("유저네임은 4자 이상, 10자 이하이어야 합니다.");
    }

    @Test
    @DisplayName("SignupRequestDto 필드 username 유효성 검사 테스트 - Pattern")
    void testUsernameFieldPattern() {
        // given
        SignupRequestDto dto = createSignupRequestDto();
        dto.setUsername("user@name"); // @Pattern 검사를 유발

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("유저네임은 소문자 알파벳과 숫자만 포함해야 합니다.");
    }

    @Test
    @DisplayName("SignupRequestDto 필드 password 유효성 검사 테스트 - NotBlank")
    void testPasswordFieldNotBlank() {
        // given
        SignupRequestDto dto = createSignupRequestDto();
        dto.setPassword(null); // @NotBlank 검사를 유발

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("비밀번호는 필수 입력 값입니다.");
    }

    @Test
    @DisplayName("SignupRequestDto 필드 password 유효성 검사 테스트 - Size")
    void testPasswordFieldSize() {
        // given
        SignupRequestDto dto = createSignupRequestDto();
        dto.setPassword("Short1!"); // @Size 검사를 유발

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("비밀번호는 8자 이상, 15자 이하이어야 합니다.");
    }

    @Test
    @DisplayName("SignupRequestDto 필드 password 유효성 검사 테스트 - Pattern")
    void testPasswordFieldPattern() {
        // given
        SignupRequestDto dto = createSignupRequestDto();
        dto.setPassword("password123"); // @Pattern 검사를 유발

        // when
        Set<ConstraintViolation<SignupRequestDto>> violations = validator.validate(dto);

        // then
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage()).isEqualTo("비밀번호는 최소 하나의 대문자, 소문자, 숫자, 특수문자를 포함해야 합니다.");
    }

    @Test
    @DisplayName("SignupRequestDto Getter 메서드 테스트")
    void testGetters() {
        // given
        SignupRequestDto dto = createSignupRequestDto();

        // when & then
        assertThat(dto.getUsername()).isEqualTo("testuser");
        assertThat(dto.getPassword()).isEqualTo("Password1!");
        assertThat(dto.isAdmin()).isFalse();
        assertThat(dto.getAdminPassword()).isEqualTo("");
    }
}
