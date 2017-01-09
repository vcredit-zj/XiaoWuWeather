package com.wyw.xiaowuweather.csdn;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：另外的一些链接,不是基于baseUrl,使用 @url 直接传入url即可
 * 创建人：伍跃武
 * 创建时间：2017/1/9 17:42
 */
public interface AnotherAPI {
    /*
     请求该接口：https://api.github.com/users/wuyuewu515
   */
    @GET("users/{user}")
    Call<User> userInfo(@Path("user") String user);

}
