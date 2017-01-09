package com.wyw.xiaowuweather.csdn.interceptor;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：日志拦截器
 * 创建人：伍跃武
 * 创建时间：2017/1/6 16:56
 */
public final class HttpLoggingInterceptor implements Interceptor {

    public enum Level {
        BODY;
    }

    public HttpLoggingInterceptor(Logger logger) {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        return chain.proceed(request);
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        return this;
    }

    interface Logger {
        void log(String name, int type);
    }

}
