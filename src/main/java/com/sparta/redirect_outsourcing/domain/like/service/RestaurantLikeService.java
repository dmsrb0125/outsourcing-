package com.sparta.redirect_outsourcing.domain.like.service;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.RestaurantLike;
import com.sparta.redirect_outsourcing.domain.like.repository.LikeAdapter;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
import com.sparta.redirect_outsourcing.domain.restaurant.repository.RestaurantAdapter;
import com.sparta.redirect_outsourcing.domain.user.entity.User;
import com.sparta.redirect_outsourcing.domain.user.repository.UserAdapter;
import com.sparta.redirect_outsourcing.exception.custom.like.AlreadyLikedException;
import com.sparta.redirect_outsourcing.exception.custom.like.SelfLikeException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantLikeService {
    private final LikeAdapter likeAdapter;
    private final RestaurantAdapter restaurantAdapter;
    private final UserAdapter userAdapter;

    @Transactional
    public void addRestaurantLike(Long userId, Long restaurantId) {
        User user = userAdapter.findById(userId);
        Restaurant restaurant = restaurantAdapter.findById(restaurantId);

        // 자신이 등록한 레스토랑인지 확인
        if (restaurant.getUser().getId().equals(userId)) {
            throw new SelfLikeException(ResponseCodeEnum.USER_CANNOT_LIKE_OWN_REVIEW);
        }

        if (likeAdapter.existsByUserAndRestaurant(user, restaurant)) {
            throw new AlreadyLikedException(ResponseCodeEnum.USER_ALREADY_LIKED);
        }

        RestaurantLike restaurantLike = new RestaurantLike();
        restaurantLike.setUser(user);
        restaurantLike.setRestaurant(restaurant);
        likeAdapter.saveRestaurantLike(restaurantLike);

        // 레스토랑 좋아요 수 업데이트
        restaurantAdapter.updateRestaurantLikeCount(restaurantId);
    }

    @Transactional
    public void removeRestaurantLike(Long userId, Long restaurantId) {
        likeAdapter.deleteRestaurantLike(userId, restaurantId);

        // 레스토랑 좋아요 수 업데이트
        restaurantAdapter.updateRestaurantLikeCount(restaurantId);
    }
}
