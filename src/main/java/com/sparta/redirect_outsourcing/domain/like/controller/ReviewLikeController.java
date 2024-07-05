package com.sparta.redirect_outsourcing.domain.like.controller;

import com.sparta.redirect_outsourcing.auth.UserDetailsImpl;
import com.sparta.redirect_outsourcing.common.MessageResponseDto;
import com.sparta.redirect_outsourcing.common.ResponseUtils;
import com.sparta.redirect_outsourcing.domain.like.service.ReviewLikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewLikeController {
    private final ReviewLikeService reviewLikeService;

    // 리뷰에 좋아요 달기
    @PostMapping("/{reviewId}/likes")
    public ResponseEntity<MessageResponseDto> addReviewLike(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl loginUser) {
        reviewLikeService.addReviewLike(loginUser.getUser().getId(), reviewId);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 등록 성공");
    }

    // 리뷰에 좋아요 삭제
    @DeleteMapping("/{reviewId}/likes")
    public ResponseEntity<MessageResponseDto> removeReviewLike(@PathVariable Long reviewId, @AuthenticationPrincipal UserDetailsImpl loginUser) {
        reviewLikeService.removeReviewLike(loginUser.getUser().getId(), reviewId);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 삭제 성공");
    }
}
