package com.sparta.redirect_outsourcing.domain.like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.domain.like.entity.RestaurantLike;
import com.sparta.redirect_outsourcing.domain.like.entity.ReviewLike;
import com.sparta.redirect_outsourcing.domain.restaurant.dto.responseDto.RestaurantResponseDto;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
import com.sparta.redirect_outsourcing.domain.review.dto.ReviewResponseDto;
import com.sparta.redirect_outsourcing.domain.review.entity.Review;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class LikeAdapter {

    private final ReviewLikeRepository reviewLikeRepository;
    private final RestaurantLikeRepository restaurantLikeRepository;
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

    public RestaurantLike saveRestaurantLike(RestaurantLike restaurantLike) {
        return restaurantLikeRepository.save(restaurantLike);
    }

    public void deleteRestaurantLike(Long userId, Long restaurantId) {
        restaurantLikeRepository.deleteByUserIdAndRestaurantId(userId, restaurantId);
    }

    public boolean existsByUserAndRestaurant(User user, Restaurant restaurant) {
        return restaurantLikeRepository.existsByUserAndRestaurant(user, restaurant);
    }

    public Page<RestaurantResponseDto> findLikedRestaurants(User user, Pageable pageable) {
        List<Restaurant> restaurants = restaurantLikeRepository.findByUser(user, pageable).stream()
                .map(RestaurantLike::getRestaurant)
                .collect(Collectors.toList());
        List<RestaurantResponseDto> responseDtos = restaurants.stream()
                .map(RestaurantResponseDto::new)
                .collect(Collectors.toList());
        return new PageImpl<>(responseDtos, pageable, responseDtos.size());
    }

    public Page<ReviewResponseDto> findLikedReviews(User user, Pageable pageable) {
        List<Review> reviews = reviewLikeRepository.findByUser(user, pageable).stream()
                .map(ReviewLike::getReview)
                .collect(Collectors.toList());
        List<ReviewResponseDto> responseDtos = reviews.stream()
                .map(ReviewResponseDto::of)
                .collect(Collectors.toList());
        return new PageImpl<>(responseDtos, pageable, responseDtos.size());
    }
}
