package com.wyw.xiaowuweather.service;

import com.wyw.xiaowuweather.enties.NowWetherInfo;
import com.wyw.xiaowuweather.enties.WetherResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 项目名称：XiaoWuWeather
 * 类描述： 测试类
 * 创建人：伍跃武
 * 创建时间：2016/11/28 14:54
 */
public interface RequesService {
    // 测试网址 http://tj.nineton.cn/Api/Config/getConfig?appid=2&cid=ireadercitybooksdata
    @GET("Api/Config/getConfig")
    Call<String> getString(@Query("appid") String appid, @Query("cid") String cid);

    // https://api.thinkpage.cn/v3/weather/now.json?key=UXTE015ITO&location=beijing&language=zh-Hans&unit=c
    @GET("weather/now.json")
    Call<WetherResult<NowWetherInfo>> getNowWether(@Query("key") String key, @Query("location") String location);
}
