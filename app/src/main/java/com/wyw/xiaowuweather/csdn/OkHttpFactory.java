package com.wyw.xiaowuweather.csdn;

import com.wyw.xiaowuweather.WeatherApplication;
import com.wyw.xiaowuweather.csdn.interceptor.HttpLoggingInterceptor;
import com.wyw.xiaowuweather.csdn.interceptor.LogInterceptor;
import com.wyw.xiaowuweather.csdn.interceptor.UserAgentInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：初始化okhttpclient，并且对外提供该实例的单例
 * 创建人：伍跃武
 * http://blog.csdn.net/u014099894/article/details/51441462
 * 创建时间：2017/1/6 16:47
 */
public enum OkHttpFactory {
    INSTANCE;
    private final OkHttpClient okHttpClient;
    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;

    OkHttpFactory() {

        //创建打印拦截器
        HttpLoggingInterceptor interceptor =
                new HttpLoggingInterceptor(LogInterceptor.INSTANCE);
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置缓存
        Cache cache = new Cache(WeatherApplication.mContext.getCacheDir(), 10 * 1024 * 1024);

        okHttpClient = new OkHttpClient.Builder()
                //添加打印拦截器
                .addInterceptor(interceptor)
                //添加UA
                .addInterceptor(new UserAgentInterceptor(HttpHelper.getUserAgent()))
                //失败重连
                .retryOnConnectionFailure(true)
                //设置可以在chrome中可以查看请求
                //.addNetworkInterceptor(new StethoInterceptor());
                //设置缓存目录
                .cache(cache)
                //请求超时
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
