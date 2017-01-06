package com.wyw.xiaowuweather.csdn.interceptor;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：日志拦截器
 * 创建人：伍跃武
 *
 * https://github.com/baiiu/LearnTechDemo/blob/c0cd007e2f2e5b3d0c632be30d5f9b0e0032946d/lib_component/LogUtilRelease/src/main/java/com/baiiu/library/okHttpLog/HttpLoggingInterceptorM.java
 * 创建时间：2017/1/6 16:56
 */
public final class HttpLoggingInterceptor implements Interceptor {
    public HttpLoggingInterceptor(Logger logger) {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }
}
