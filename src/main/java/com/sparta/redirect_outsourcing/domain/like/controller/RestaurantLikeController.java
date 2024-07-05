package com.sparta.redirect_outsourcing.domain.like.controller;

import com.sparta.redirect_outsourcing.auth.UserDetailsImpl;
import com.sparta.redirect_outsourcing.common.MessageResponseDto;
import com.sparta.redirect_outsourcing.common.ResponseUtils;
import com.sparta.redirect_outsourcing.domain.like.service.RestaurantLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantLikeController {
    private final RestaurantLikeService restaurantLikeService;

    // 음식점에 좋아요 달기
    @PostMapping("/{restaurantId}/likes")
    public ResponseEntity<MessageResponseDto> addRestaurantLike(@PathVariable Long restaurantId, @AuthenticationPrincipal UserDetailsImpl loginUser) {
        restaurantLikeService.addRestaurantLike(loginUser.getUser().getId(), restaurantId);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 등록 성공");
    }

    // 음식점에 좋아요 삭제
    @DeleteMapping("/{restaurantId}/likes")
    public ResponseEntity<MessageResponseDto> removeRestaurantLike(@PathVariable Long restaurantId, @AuthenticationPrincipal UserDetailsImpl loginUser) {
        restaurantLikeService.removeRestaurantLike(loginUser.getUser().getId(), restaurantId);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 삭제 성공");
    }
}
