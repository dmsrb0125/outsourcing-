package com.sparta.redirect_outsourcing.domain.like.repository;

import com.sparta.redirect_outsourcing.domain.like.entity.RestaurantLike;
import com.sparta.redirect_outsourcing.domain.like.entity.ReviewLike;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
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
    private final RestaurantLikeRepository restaurantLikeRepository;

    public ReviewLike saveReviewLike(ReviewLike reviewLike) {
        return reviewLikeRepository.save(reviewLike);
    }

    public void deleteReviewLike(Long userId, Long reviewId) {
        reviewLikeRepository.deleteByUserIdAndReviewId(userId, reviewId);
    }

    public boolean existsByUserAndReview(User user, Review review) {
        return reviewLikeRepository.existsByUserAndReview(user, review);
    }

    public RestaurantLike saveRestaurantLike(RestaurantLike restaurantLike) {
        return restaurantLikeRepository.save(restaurantLike);
    }

    public void deleteRestaurantLike(Long userId, Long restaurantId) {
        restaurantLikeRepository.deleteByUserIdAndRestaurantId(userId, restaurantId);
    }

    public boolean existsByUserAndRestaurant(User user, Restaurant restaurant) {
        return restaurantLikeRepository.existsByUserAndRestaurant(user, restaurant);
    }
}
