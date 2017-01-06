package com.wyw.xiaowuweather.csdn;

import com.wyw.xiaowuweather.csdn.interceptor.HttpLoggingInterceptor;

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
        //   HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        okHttpClient = null;
    }
}
