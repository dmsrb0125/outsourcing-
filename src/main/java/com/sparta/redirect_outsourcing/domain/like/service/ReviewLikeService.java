package com.sparta.redirect_outsourcing.domain.like.service;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.ReviewLike;
import com.sparta.redirect_outsourcing.domain.like.repository.LikeAdapter;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.domain.review.repository.ReviewAdapter;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.domain.user.repository.UserAdapter;
import com.sparta.redirect_outsourcing.exception.custom.like.AlreadyLikedException;
import com.sparta.redirect_outsourcing.exception.custom.like.SelfLikeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewLikeService {
    private final LikeAdapter likeAdapter;
    private final ReviewAdapter reviewAdapter;
    private final UserAdapter userAdapter;

    @Transactional
    public void addReviewLike(Long userId, Long reviewId) {
        User user = userAdapter.findById(userId);
        Review review = reviewAdapter.findById(reviewId);

        // 자신이 작성한 리뷰인지 확인
        if (review.getUser().getId().equals(userId)) {
            throw new SelfLikeException(ResponseCodeEnum.USER_CANNOT_LIKE_OWN_REVIEW);
        }

        if (likeAdapter.existsByUserAndReview(user, review)) {
            throw new AlreadyLikedException(ResponseCodeEnum.USER_ALREADY_LIKED);
        }

        ReviewLike reviewLike = new ReviewLike();
        reviewLike.setUser(user);
        reviewLike.setReview(review);
        likeAdapter.saveReviewLike(reviewLike);

        // 리뷰 좋아요 수 업데이트
        likeAdapter.updateReviewLikeCount(reviewId);
    }

    @Transactional
    public void removeReviewLike(Long userId, Long reviewId) {
        likeAdapter.deleteReviewLike(userId, reviewId);

        // 리뷰 좋아요 수 업데이트
        likeAdapter.updateReviewLikeCount(reviewId);
    }
}
