package com.sparta.redirect_outsourcing.domain.restaurant.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;
import com.sparta.redirect_outsourcing.domain.like.entity.QRestaurantLike;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.QRestaurant;
import com.sparta.redirect_outsourcing.domain.restaurant.entity.Restaurant;
import com.sparta.redirect_outsourcing.exception.custom.restaurant.RestaurantNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestaurantAdapter {

    private final RestaurantRepository restaurantRepository;
    private final JPAQueryFactory queryFactory;

    public Restaurant findById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new RestaurantNotFoundException(ResponseCodeEnum.RESTAURANT_NOT_EXIST));
    }

    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public void delete(Restaurant restaurant) {
        restaurantRepository.delete(restaurant);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public void updateRestaurantLikeCount(Long restaurantId) {
        QRestaurantLike restaurantLike = QRestaurantLike.restaurantLike;

        long likeCount = queryFactory
                .select(restaurantLike.count())
                .from(restaurantLike)
                .where(restaurantLike.restaurant.id.eq(restaurantId))
                .fetchOne();

        queryFactory.update(QRestaurant.restaurant)
                .set(QRestaurant.restaurant.likeCount, (int) likeCount)
                .where(QRestaurant.restaurant.id.eq(restaurantId))
                .execute();
    }
}
