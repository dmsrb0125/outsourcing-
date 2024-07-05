package com.sparta.redirect_outsourcing.domain.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.QReviewLike;
import com.sparta.redirect_outsourcing.domain.review.entity.QReview;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.exception.custom.review.ReviewException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewAdapter {
    private final ReviewRepository reviewRepository;
    private final JPAQueryFactory queryFactory;

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public void delete(Review review){
        reviewRepository.delete(review);
    }

    public Review findById(Long id){
        return reviewRepository.findById(id)
                .orElseThrow(()-> new ReviewException(ResponseCodeEnum.REVIEW_NOT_FOUND));
    }

    public List<Review> findAll(){
        return reviewRepository.findAll();
    }

    public List<Review> findByRestaurantId(Long restaurantId){
        return reviewRepository.findAllByRestaurantId(restaurantId);
    }

    public void updateLikeCount(Long reviewId) {
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
