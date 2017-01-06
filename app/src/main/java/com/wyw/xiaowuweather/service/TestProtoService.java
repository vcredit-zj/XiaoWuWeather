package com.wyw.xiaowuweather.service;

import com.vcredit.zj.bean.proto.BookOuterClass;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by shibenli on 2017/1/5.
 */

public interface TestProtoService {

    @GET("hello-world/proto")
    Call<BookOuterClass.Book> getBook();
}
