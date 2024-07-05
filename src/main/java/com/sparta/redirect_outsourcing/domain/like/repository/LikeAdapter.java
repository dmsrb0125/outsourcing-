package com.sparta.redirect_outsourcing.domain.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.domain.like.entity.QReviewLike;
import com.sparta.redirect_outsourcing.domain.like.entity.ReviewLike;
import com.sparta.redirect_outsourcing.domain.review.entity.QReview;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeAdapter {

    private final ReviewLikeRepository reviewLikeRepository;
    private final JPAQueryFactory queryFactory;

    public ReviewLike saveReviewLike(ReviewLike reviewLike) {
        return reviewLikeRepository.save(reviewLike);
    }

    public void deleteReviewLike(Long userId, Long reviewId) {
        reviewLikeRepository.deleteByUserIdAndReviewId(userId, reviewId);
    }

    public boolean existsByUserAndReview(User user, Review review) {
        return reviewLikeRepository.existsByUserAndReview(user, review);
    }

    public void updateReviewLikeCount(Long reviewId) {
        QReviewLike reviewLike = QReviewLike.reviewLike;

        long likeCount = queryFactory
                .select(reviewLike.count())
                .from(reviewLike)
                .where(reviewLike.review.id.eq(reviewId))
                .fetchOne();

        queryFactory.update(QReview.review)
                .set(QReview.review.likeCount, (int) likeCount)
                .where(QReview.review.id.eq(reviewId))
                .execute();
    }
}
