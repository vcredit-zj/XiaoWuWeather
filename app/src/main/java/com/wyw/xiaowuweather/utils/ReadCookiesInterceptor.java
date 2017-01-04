package com.wyw.xiaowuweather.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：拦截器中添加cookie
 * 创建人：伍跃武
 * 创建时间：2017/1/4 16:38
 */
public class ReadCookiesInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";

    protected Context context;

    public ReadCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> preferences =
                (HashSet<String>) PreferenceManager.getDefaultSharedPreferences(context).getStringSet(PREF_COOKIES, new HashSet<String>());
        for (String cookie : preferences) {
            builder.addHeader("Cookie", cookie);
        }
        return chain.proceed(builder.build());
    }
}
