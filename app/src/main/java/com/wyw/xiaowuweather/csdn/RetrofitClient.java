package com.wyw.xiaowuweather.csdn;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目名称：XiaoWuWeather
 * 类描述： 初始化retrofit
 * 创建人：伍跃武
 * 创建时间：2017/1/9 17:32
 */
public enum RetrofitClient implements ApiContants {
    INSTANCE;

    private final Retrofit retrofit;

    RetrofitClient() {
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OkHttpFactory.INSTANCE.getOkHttpClient())
                .baseUrl(GITHUB_BASEURL)
                //创建GSON转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
