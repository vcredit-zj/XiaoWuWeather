package com.wyw.xiaowuweather;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：
 * 创建人：伍跃武
 * 创建时间：2017/1/9 17:17
 */
public class WeatherApplication extends Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
