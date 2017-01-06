package com.wyw.xiaowuweather.csdn;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：gitHub 用户信息
 * 创建人：伍跃武
 * 创建时间：2017/1/6 16:30
 */
public class User implements Serializable {
    /**
     * "login": "wuyuewu515",
     * "id": 22491159,
     * "type": "User",
     * "site_admin": false,
     * "name": "伍跃武",
     * "company": null,
     * "blog": null,
     * "location": null,
     * "email": null,
     * "hireable": null,
     * "bio": null,
     * "public_repos": 1,
     * "public_gists": 0,
     * "followers": 0,
     * "following": 0,
     * "created_at": "2016-09-28T06:32:25Z",
     * "updated_at": "2016-10-26T05:37:41Z"
     */
    @Expose
    protected String login;
    @Expose
    protected String id;
    @Expose
    protected String type;
    @Expose
    protected String name;

}
