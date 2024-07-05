package com.sparta.redirect_outsourcing.domain.like.repository;

import com.sparta.redirect_outsourcing.domain.like.entity.ReviewLike;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    void deleteByUserIdAndReviewId(Long userId, Long reviewId);
    boolean existsByUserAndReview(User user, Review review);
}
