package com.sparta.redirect_outsourcing.domain.like.controller;

import com.sparta.redirect_outsourcing.auth.UserDetailsImpl;
import com.sparta.redirect_outsourcing.common.DataResponseDto;
import com.sparta.redirect_outsourcing.common.MessageResponseDto;
import com.sparta.redirect_outsourcing.common.ResponseUtils;
import com.sparta.redirect_outsourcing.domain.like.service.RestaurantLikeService;
import com.sparta.redirect_outsourcing.domain.restaurant.dto.responseDto.RestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/{restaurantId}/likes")
    public ResponseEntity<MessageResponseDto> addRestaurantLike(
            @PathVariable Long restaurantId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        restaurantLikeService.addRestaurantLike(userDetails.getUser().getId(), restaurantId);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 등록 성공");
    }

    @DeleteMapping("/{restaurantId}/likes")
    public ResponseEntity<MessageResponseDto> removeRestaurantLike(
            @PathVariable Long restaurantId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        restaurantLikeService.removeRestaurantLike(userDetails.getUser().getId(), restaurantId);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 삭제 성공");
    }

    @GetMapping("/likes")
    public ResponseEntity<DataResponseDto<Page<RestaurantResponseDto>>> getLikedRestaurants(
            @AuthenticationPrincipal UserDetailsImpl userDetails, Pageable pageable
    ) {
        Page<RestaurantResponseDto> likedRestaurants = restaurantLikeService.getLikedRestaurants(userDetails.getUser(), pageable);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 한 음식점 목록 조회 성공", likedRestaurants);
    }
}
