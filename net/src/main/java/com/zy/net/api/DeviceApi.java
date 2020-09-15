package com.zy.net.api;

import com.zy.net.protocol.request.DeviceInfo;
import com.zy.net.protocol.response.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author:zhangyue
 * @date:2020/9/11
 */
public interface DeviceApi {

    /**
     * 注册设备
     * @param info
     * @return
     */
    @POST("/device/addDevice")
    Observable<BaseEntity<String>> registerDevice(@Body DeviceInfo info);
}
