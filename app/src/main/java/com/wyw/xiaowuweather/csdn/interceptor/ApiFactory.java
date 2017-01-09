package com.wyw.xiaowuweather.csdn.interceptor;

import com.wyw.xiaowuweather.csdn.AnotherAPI;
import com.wyw.xiaowuweather.csdn.GitHubAPI;
import com.wyw.xiaowuweather.csdn.RetrofitClient;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：创建ApiFactory 封装所有的API
 * 创建人：伍跃武
 * 创建时间：2017/1/9 17:40
 */
public enum ApiFactory {
    INSTACE;
    private static GitHubAPI gitHubAPI;
    private static AnotherAPI anotherAPI;

    ApiFactory() {
    }

    public static GitHubAPI getGitHubAPI() {
        if (null == gitHubAPI) {
            gitHubAPI = RetrofitClient.INSTANCE.getRetrofit().create(GitHubAPI.class);
        }
        return gitHubAPI;
    }

    public static AnotherAPI getAnotherAPI() {
        if (null == anotherAPI) {
            anotherAPI = RetrofitClient.INSTANCE.getRetrofit().create(AnotherAPI.class);
        }
        return anotherAPI;
    }
}
