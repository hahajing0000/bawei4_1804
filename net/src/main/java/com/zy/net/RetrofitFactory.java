package com.zy.net;

import com.zy.net.retrofit.CustomGsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author:zhangyue
 * @date:2020/9/10
 * @Description 网络请求工厂  DoubleCheck 单例模式实现
 */
public class RetrofitFactory {
    private static volatile RetrofitFactory instance=null;
    private RetrofitFactory(){
        initRetrofit();
    }
    public static RetrofitFactory getInstance(){
        if (null==instance){
            synchronized (RetrofitFactory.class){
                if (null==instance){
                    instance=new RetrofitFactory();
                }
            }
        }
        return instance;
    }

    private Retrofit retrofit;

    /**
     * 初始化Retrofit
     */
    private void initRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(BuildConfig.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomGsonConverterFactory.create())
                .client(createOkHttpClient())
                .build();

    }

    /**
     * 创建OkHttpClient客户端
     * @return
     */
    private OkHttpClient createOkHttpClient() {
        OkHttpClient client=new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(createTokenAndDeviceCodeInterceptor())
                .addNetworkInterceptor(createNetWorkInterceptor())
                .build();
        return client;
    }


    /**
     * 处理Token及设备编码  ——  加入到请求头
     * @return
     */
    private Interceptor createTokenAndDeviceCodeInterceptor() {
       Interceptor interceptor= new Interceptor(){

            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request newRequest = request.newBuilder()
                        .addHeader("token","11")
                        .addHeader("devicecode","11")
                        .build();
                Response response = chain.proceed(newRequest);

                return response;
            }
        };
        return interceptor;
    }

    /**
     * 创建日志拦截器 并指定等级 Body级
     * @return
     */
    private Interceptor createNetWorkInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }


}
