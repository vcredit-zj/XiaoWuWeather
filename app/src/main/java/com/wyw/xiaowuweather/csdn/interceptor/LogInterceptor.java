package com.wyw.xiaowuweather.csdn.interceptor;


/**
 * 项目名称：XiaoWuWeather
 * 类描述： 打印拦截器
 * 创建人：伍跃武
 * 创建时间：2017/1/9 17:11
 */
public enum LogInterceptor implements HttpLoggingInterceptor.Logger {
    INSTANCE;

    @Override
    public void log(String name, int type) {

    }
}
