package com.wyw.xiaowuweather.csdn.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：使用拦截器
 * 创建人：伍跃武
 * 创建时间：2017/1/6 16:34
 */
public final class UserAgentInterceptor implements Interceptor {

    private static final String USER_AGENT_HEADER_NAME = "User-Agent";
    private final String userAgentHeaderValue;

    public UserAgentInterceptor(String userAgentHeaderValue) {
        this.userAgentHeaderValue = userAgentHeaderValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final Request originalRequest = chain.request();
        final Request requestWithUserAgent = originalRequest.newBuilder()
                //移除之前默认的用户代理
                .removeHeader(USER_AGENT_HEADER_NAME)
                //设置用户代理
                .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)
                .build();
        return chain.proceed(requestWithUserAgent);
    }
}
