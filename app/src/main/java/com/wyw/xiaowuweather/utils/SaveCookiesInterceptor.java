package com.wyw.xiaowuweather.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：保存cookie
 * 创建人：伍跃武
 * 创建时间：2017/1/4 17:20
 */
public class SaveCookiesInterceptor implements Interceptor {
    public static final String PREF_COOKIES = "PREF_COOKIES";

    protected Context context;

    public SaveCookiesInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        if (!originalResponse.headers("Set_Cookie").isEmpty()) {
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set_Cookie")) {
                cookies.add(header);
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putStringSet(PREF_COOKIES, cookies)
                    .apply();
        }
        return originalResponse;
    }
}
