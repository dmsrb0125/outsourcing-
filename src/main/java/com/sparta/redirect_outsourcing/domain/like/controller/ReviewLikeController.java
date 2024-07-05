package com.sparta.redirect_outsourcing.domain.like.controller;

import com.sparta.redirect_outsourcing.auth.UserDetailsImpl;
import com.sparta.redirect_outsourcing.common.DataResponseDto;
import com.sparta.redirect_outsourcing.common.MessageResponseDto;
import com.sparta.redirect_outsourcing.common.ResponseUtils;
import com.sparta.redirect_outsourcing.domain.like.service.ReviewLikeService;
import com.sparta.redirect_outsourcing.domain.review.dto.ReviewResponseDto;
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
@RequestMapping("/reviews")
public class ReviewLikeController {
    private final ReviewLikeService reviewLikeService;

    @PostMapping("/{reviewId}/likes")
    public ResponseEntity<MessageResponseDto> addReviewLike(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewLikeService.addReviewLike(userDetails.getUser().getId(), reviewId);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 등록 성공");
    }

    @DeleteMapping("/{reviewId}/likes")
    public ResponseEntity<MessageResponseDto> removeReviewLike(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        reviewLikeService.removeReviewLike(userDetails.getUser().getId(), reviewId);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 삭제 성공");
    }

    @GetMapping("/likes")
    public ResponseEntity<DataResponseDto<Page<ReviewResponseDto>>> getLikedReviews(
            @AuthenticationPrincipal UserDetailsImpl userDetails, Pageable pageable
    ) {
        Page<ReviewResponseDto> likedReviews = reviewLikeService.findLikedReviews(userDetails.getUser(), pageable);
        return ResponseUtils.of(HttpStatus.OK, "좋아요 한 리뷰 목록 조회 성공", likedReviews);
    }
}
