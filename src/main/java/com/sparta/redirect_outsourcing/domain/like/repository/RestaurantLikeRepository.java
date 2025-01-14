package com.sparta.redirect_outsourcing.domain.like.repository;

import com.sparta.redirect_outsourcing.domain.like.entity.RestaurantLike;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantLikeRepository extends JpaRepository<RestaurantLike, Long> {
    void deleteByUserIdAndRestaurantId(Long userId, Long restaurantId);
    boolean existsByUserAndRestaurant(User user, Restaurant restaurant);
    List<RestaurantLike> findByUser(User user, Pageable pageable);
}
