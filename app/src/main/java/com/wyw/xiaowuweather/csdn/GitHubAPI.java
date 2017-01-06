package com.wyw.xiaowuweather.csdn;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：创建接口，声明api
 * 创建人：伍跃武
 * 创建时间：2017/1/6 16:27
 */
public interface GitHubAPI {
    /*
       请求该接口：https://api.github.com/users/wuyuewu515
     */
    @GET("users/{user}")
    Call<User> userInfo(@Path("user") String user);

}
