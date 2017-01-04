package com.wyw.xiaowuweather.enties;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 项目名称：XiaoWuWeather
 * 类描述： 实时天气
 * 创建人：伍跃武
 * 创建时间：2016/12/29 14:10
 */
public class NowWetherInfo implements Serializable {
    @Expose
    protected String last_update;
    @Expose
    protected CityLocationInfo location;
    @Expose
    protected NowInfo now;


    public String getLast_update() {
        return last_update;
    }

    public NowWetherInfo setLast_update(String last_update) {
        this.last_update = last_update;
        return this;
    }

    public CityLocationInfo getLocation() {
        return location;
    }

    public NowWetherInfo setLocation(CityLocationInfo location) {
        this.location = location;
        return this;
    }

    public NowInfo getNow() {
        return now;
    }

    public NowWetherInfo setNow(NowInfo now) {
        this.now = now;
        return this;
    }

    @Override
    public String toString() {
        return "NowWetherInfo{" +
                "last_update='" + last_update + '\'' +
                ", location=" + location +
                ", now=" + now +
                '}';
    }
}

/**
 * 当前实况信息
 */
class NowInfo implements Serializable {

    /**
     * 天气现象代码
     */
    @Expose
    protected String code;
    /**
     * 温度
     */
    @Expose
    protected String temperature;
    /**
     * 体感温度
     */
    @Expose
    protected String feels_like;


    public String getCode() {
        return code;
    }

    public NowInfo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getTemperature() {
        return temperature;
    }

    public NowInfo setTemperature(String temperature) {
        this.temperature = temperature;
        return this;
    }

    public String getFeels_like() {
        return feels_like;
    }

    public NowInfo setFeels_like(String feels_like) {
        this.feels_like = feels_like;
        return this;
    }

    @Override
    public String toString() {
        return "NowInfo{" +
                "code='" + code + '\'' +
                ", temperature='" + temperature + '\'' +
                ", feels_like='" + feels_like + '\'' +
                '}';
    }
}