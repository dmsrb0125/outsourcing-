package com.sparta.redirect_outsourcing.domain.user.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private User createUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setNickname("testNickname");
        user.setIntroduce("Hello, I am a test user.");
        user.setPictureUrl("https://example.com/picture.jpg");
        user.setUserStatus(UserStatusEnum.STATUS_NORMAL);
        user.setUserRole(UserRoleEnum.ROLE_USER);
        user.setRefreshToken("testRefreshToken");
        user.setKakaoId(123456L);  // Long 타입으로 변경
        user.setPreviousPasswords(List.of("oldPassword1", "oldPassword2"));
        return user;
    }

    @Test
    @DisplayName("User 엔티티 생성 테스트")
    void testUserCreation() {
        // given
        User user = createUser();

        // when & then
        assertThat(user.getUsername()).isEqualTo("testUser");
        assertThat(user.getPassword()).isEqualTo("testPassword");
        assertThat(user.getNickname()).isEqualTo("testNickname");
        assertThat(user.getIntroduce()).isEqualTo("Hello, I am a test user.");
        assertThat(user.getPictureUrl()).isEqualTo("https://example.com/picture.jpg");
        assertThat(user.getUserStatus()).isEqualTo(UserStatusEnum.STATUS_NORMAL);
        assertThat(user.getUserRole()).isEqualTo(UserRoleEnum.ROLE_USER);
        assertThat(user.getRefreshToken()).isEqualTo("testRefreshToken");
        assertThat(user.getKakaoId()).isEqualTo(123456L);  // Long 타입으로 변경
        assertThat(user.getPreviousPasswords()).containsExactly("oldPassword1", "oldPassword2");
    }

    @Test
    @DisplayName("User 필드 username 유효성 검사 테스트")
    void testUsernameFieldConstraints() {
        // given
        User user = createUser();
        user.setUsername(null);

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(1);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("username");
        assertThat(violation.getMessage()).isEqualTo("널이어서는 안됩니다");
    }

    @Test
    @DisplayName("User 필드 password 유효성 검사 테스트")
    void testPasswordFieldConstraints() {
        // given
        User user = createUser();
        user.setPassword(null);

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(1);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("password");
        assertThat(violation.getMessage()).isEqualTo("널이어서는 안됩니다");
    }

    @Test
    @DisplayName("User 필드 userStatus 유효성 검사 테스트")
    void testUserStatusFieldConstraints() {
        // given
        User user = createUser();
        user.setUserStatus(null);

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(1);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("userStatus");
        assertThat(violation.getMessage()).isEqualTo("널이어서는 안됩니다");
    }

    @Test
    @DisplayName("User 필드 userRole 유효성 검사 테스트")
    void testUserRoleFieldConstraints() {
        // given
        User user = createUser();
        user.setUserRole(null);

        // when
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        // then
        assertThat(violations.size()).isEqualTo(1);

        ConstraintViolation<User> violation = violations.iterator().next();
        assertThat(violation.getPropertyPath().toString()).isEqualTo("userRole");
        assertThat(violation.getMessage()).isEqualTo("널이어서는 안됩니다");
    }

    @Test
    @DisplayName("User Getter 및 Setter 테스트")
    void testGettersAndSetters() {
        // given
        User user = new User();

        // when
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setNickname("testNickname");
        user.setIntroduce("Hello, I am a test user.");
        user.setPictureUrl("https://example.com/picture.jpg");
        user.setUserStatus(UserStatusEnum.STATUS_NORMAL);
        user.setUserRole(UserRoleEnum.ROLE_USER);
        user.setRefreshToken("testRefreshToken");
        user.setKakaoId(123456L);  // Long 타입으로 변경
        user.setPreviousPasswords(List.of("oldPassword1", "oldPassword2"));

        // then
        assertThat(user.getUsername()).isEqualTo("testUser");
        assertThat(user.getPassword()).isEqualTo("testPassword");
        assertThat(user.getNickname()).isEqualTo("testNickname");
        assertThat(user.getIntroduce()).isEqualTo("Hello, I am a test user.");
        assertThat(user.getPictureUrl()).isEqualTo("https://example.com/picture.jpg");
        assertThat(user.getUserStatus()).isEqualTo(UserStatusEnum.STATUS_NORMAL);
        assertThat(user.getUserRole()).isEqualTo(UserRoleEnum.ROLE_USER);
        assertThat(user.getRefreshToken()).isEqualTo("testRefreshToken");
        assertThat(user.getKakaoId()).isEqualTo(123456L);  // Long 타입으로 변경
        assertThat(user.getPreviousPasswords()).containsExactly("oldPassword1", "oldPassword2");
    }
}
