package com.wyw.xiaowuweather.enties;

import java.io.Serializable;
import java.util.List;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：
 * 创建人：伍跃武
 * 创建时间：2016/12/29 16:47
 */
public class WetherResult<T> implements Serializable {
    protected List<T> results;

    public List<T> getResults() {
        return results;
    }

    public WetherResult setResults(List<T> results) {
        this.results = results;
        return this;
    }
}
