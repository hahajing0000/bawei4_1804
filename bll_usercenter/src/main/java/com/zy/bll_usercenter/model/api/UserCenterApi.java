package com.zy.bll_usercenter.model.api;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author:zhangyue
 * @date:2020/9/10
 */
public interface UserCenterApi {

    @FormUrlEncoded
    @POST("/login")
    Observable<Boolean> login(@Query("username") String username, @Query("pwd") String pwd);
}
