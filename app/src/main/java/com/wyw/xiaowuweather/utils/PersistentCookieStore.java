package com.wyw.xiaowuweather.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * 项目名称：XiaoWuWeather
 * 类描述：用来存储okhttpCookies
 * 创建人：伍跃武
 * 创建时间：2017/1/4 14:30
 * 描述：http://www.jianshu.com/p/1a5f14b63f47
 */
public class PersistentCookieStore {
    private static final String LOG_TAG = "PersistentCookieStore";
    private static final String COOKIE_PREFS = "Cookies_Perfs";

    private final Map<String, ConcurrentHashMap<String, Cookie>> cookies;
    private final SharedPreferences cookiesPrefs;

    public PersistentCookieStore(Context context) {
        cookiesPrefs = context.getSharedPreferences(COOKIE_PREFS, 0);
        cookies = new HashMap<>();

        //将持久化的cookies 缓存到内存中去 即  map cookies
        Map<String, ?> prefsMap = cookiesPrefs.getAll();
        for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
            String[] cookieNames = TextUtils.split((String) entry.getValue(), ",");
            for (String name : cookieNames) {
                String encodedCookie = cookiesPrefs.getString(name, null);
                if (encodedCookie != null) {
                    Cookie decodedCookie = decodeCookie(encodedCookie);
                    if (decodedCookie != null) {
                        if (!cookies.containsKey(entry.getKey())) {
                            cookies.put(entry.getKey(), new ConcurrentHashMap<String, Cookie>());
                        }
                        cookies.get(entry.getKey()).put(name, decodedCookie);
                    }
                }
            }
        }
    }

    protected String getCookieToken(Cookie cookie) {
        return cookie.name() + "@" + cookie.domain();
    }

    public void add(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);

        //将cookie缓存到内存中去，如果缓存过期 就重置此cookie
        if (!cookie.persistent()) {
            if (!cookies.containsKey(url.host())) {
                cookies.put(url.host(), new ConcurrentHashMap<String, Cookie>());
            }
            cookies.get(url.host()).put(name, cookie);
        } else {
            if (cookies.containsKey(url.host())) {
                cookies.get(url.host()).remove(cookie);
            }
        }
        //将cookies持久化到本地
        SharedPreferences.Editor prefWriter = cookiesPrefs.edit();
        prefWriter.putString(url.host(), TextUtils.join(",", cookies.get(url.host()).keySet()));
        prefWriter.putString(name, encodeCookie(new OkHttpCookies(cookie)));
        prefWriter.apply();
    }

    /**
     * 获取对应的url的cookie
     *
     * @param url
     * @return
     */
    public List<Cookie> get(HttpUrl url) {
        ArrayList<Cookie> ret = new ArrayList<>();
        if (cookies.containsKey(url.host())) {
            ret.addAll(cookies.get(url.host()).values());
        }
        return ret;
    }

    /**
     * 获取所有的cookie值
     *
     * @return
     */
    public List<Cookie> getCookies() {
        ArrayList<Cookie> ret = new ArrayList<>();
        for (String key : cookies.keySet()) {
            ret.addAll(cookies.get(key).values());
        }
        return ret;
    }

    /**
     * 清空所有的cookie
     *
     * @return
     */
    public boolean removeAll() {
        SharedPreferences.Editor prefsWriter = cookiesPrefs.edit();
        prefsWriter.clear();
        prefsWriter.apply();
        cookies.clear();
        return true;
    }

    /**
     * 移除指定url的cookie
     *
     * @param url
     * @param cookie
     * @return
     */
    public boolean remove(HttpUrl url, Cookie cookie) {
        String name = getCookieToken(cookie);
        if (cookies.containsKey(url.host())
                && cookies.get(url.host()).containsKey(name)) {
            cookies.get(url.host()).remove(name);
            SharedPreferences.Editor prefsWriter = cookiesPrefs.edit();
            if (cookiesPrefs.contains(name)) {
                prefsWriter.remove(name);
            }
            prefsWriter.putString(url.host(),
                    TextUtils.join(",", cookies.get(url.host()).keySet()));
            prefsWriter.apply();
            return true;
        } else {
            return false;
        }
    }

    /**
     * cookies 序列化成 string
     *
     * @param cookie
     * @return
     */
    private String encodeCookie(OkHttpCookies cookie) {
        if (cookie == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(cookie);
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }
        return byteArrayToHexString(os.toByteArray());
    }


    /**
     * 二进制数组转16进制字符串
     *
     * @param bytes
     * @return
     */
    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }


    /**
     * 将字符串返序列化成为Cookie
     *
     * @param cookieString
     * @return
     */
    private Cookie decodeCookie(String cookieString) {
        byte[] bytes = hexStringToByteArray(cookieString);
        return null;
    }

    /**
     * 十六进制的字符串转二进制数组
     *
     * @param hexString
     * @return
     */
    private byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }
}
