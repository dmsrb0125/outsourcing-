package com.sparta.redirect_outsourcing.exception.custom.like;

import com.sparta.redirect_outsourcing.common.ResponseCodeEnum;

public class SelfLikeException extends LikeException {
    public SelfLikeException(ResponseCodeEnum responseCodeEnum) {
        super(responseCodeEnum);
    }
}
