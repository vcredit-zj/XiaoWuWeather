package com.wyw.xiaowuweather.enties;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：当前城市的信息
 * 创建人：伍跃武
 * 创建时间：2016/12/29 14:11
 */
public class CityLocationInfo implements Serializable {
    @Expose
    protected String id;
    @Expose
    protected String name;
    @Expose
    protected String country;
    @Expose
    protected String timezone;
    @Expose
    protected String timezone_offset;

    public String getId() {
        return id;
    }

    public CityLocationInfo setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityLocationInfo setName(String name) {
        this.name = name;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public CityLocationInfo setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public CityLocationInfo setTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public String getTimezone_offset() {
        return timezone_offset;
    }

    public CityLocationInfo setTimezone_offset(String timezone_offset) {
        this.timezone_offset = timezone_offset;
        return this;
    }

    @Override
    public String toString() {
        return "CityLocationInfo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", timezone='" + timezone + '\'' +
                ", timezone_offset='" + timezone_offset + '\'' +
                '}';
    }
}
