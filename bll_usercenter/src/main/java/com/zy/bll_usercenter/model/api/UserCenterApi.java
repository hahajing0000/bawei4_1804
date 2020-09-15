package com.zy.bll_usercenter.model.api;

import com.zy.bll_usercenter.model.protocol.request.UserReq;
import com.zy.net.protocol.response.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author:zhangyue
 * @date:2020/9/10
 */
public interface UserCenterApi {

    @POST("/user/login")
    Observable<BaseEntity<UserReq>> login(@Body UserReq userReq);
}
