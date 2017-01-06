package com.wyw.xiaowuweather.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.protobuf.ExtensionRegistryLite;
import com.vcredit.zj.bean.proto.BookOuterClass;
import com.wyw.xiaowuweather.BuildConfig;
import com.wyw.xiaowuweather.R;
import com.wyw.xiaowuweather.enties.NowWetherInfo;
import com.wyw.xiaowuweather.enties.WetherResult;
import com.wyw.xiaowuweather.service.RequesService;
import com.wyw.xiaowuweather.service.TestProtoService;


import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "MainActivity";
    public static String KEY = "UXTE015ITO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * 获取即时天气
     */
    public void getNowWhther() {
        //添加通用请求头
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            //日志拦截器
            builder.addNetworkInterceptor(new LogInterceptor());

        }
        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.thinkpage.cn/v3/")
                // .addConverterFactory(ScalarsConverterFactory.create())
                //添加gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ProtoConverterFactory.create())
                //  .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();

        RequesService requesService = retrofit.create(RequesService.class);
        Call<WetherResult<NowWetherInfo>> nowWether = requesService.getNowWether(KEY, "shanghai");
        nowWether.enqueue(new Callback<WetherResult<NowWetherInfo>>() {
            @Override
            public void onResponse(Call<WetherResult<NowWetherInfo>> call, Response<WetherResult<NowWetherInfo>> response) {
                Log.i(TAG, "这个是" + call.request().url());
                if (response.isSuccessful()) {
                    WetherResult wetherResult = response.body();
                    List<NowWetherInfo> wetherInfos = wetherResult.getResults();
                    Log.i(TAG, "当前实况" + wetherInfos.toString());
                }
            }

            @Override
            public void onFailure(Call<WetherResult<NowWetherInfo>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    /**
     * 获取测试proto数据
     */
    public void getProto() {
        //添加通用请求头
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            //日志拦截器
            builder.addNetworkInterceptor(new LogInterceptor());

        }
        builder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("Content-Type", "application/x-protobuf;charset=UTF-8")
                        .addHeader("Accept-Encoding", "gzip, deflate")
                        .addHeader("Connection", "keep-alive")
                        .addHeader("Accept", "*/*")
                        .build();
                return chain.proceed(request);
            }

        });
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.141.10.80")
                .addConverterFactory(ProtoConverterFactory.create())
                .client(client)
                .build();

        TestProtoService requesService = retrofit.create(TestProtoService.class);
        Call<BookOuterClass.Book> requesServiceBook = requesService.getBook();
        requesServiceBook.enqueue(new Callback<BookOuterClass.Book>() {
            @Override
            public void onResponse(Call<BookOuterClass.Book> call, Response<BookOuterClass.Book> response) {
                Log.i(TAG, "这个是" + call.request().url());
                if (response.isSuccessful()) {
                    BookOuterClass.Book body = response.body();
                    Log.i(TAG, "proto数据" + body.toString());
                }
            }

            @Override
            public void onFailure(Call<BookOuterClass.Book> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }


    @OnClick(R.id.btn)
    public void onClick() {

        getNowWhther();
        getProto();

    }
}

class LogInterceptor implements Interceptor {
    public static final String TAG = "LogInterceptor.java";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder().addHeader("key", "wyw").build();
        //the request url
        String url = request.url().toString();
        //the request method
        String method = request.method();
        long t1 = System.nanoTime();
        Log.d(TAG, String.format(Locale.getDefault(), "Sending %s request [url = %s]", method, url));
        //the request body
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            StringBuilder sb = new StringBuilder("Request Body [");
            okio.Buffer buffer = new okio.Buffer();
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            if (isPlaintext(buffer)) {
                sb.append(buffer.readString(charset));
                sb.append(" (Content-Type = ").append(contentType.toString()).append(",")
                        .append(requestBody.contentLength()).append("-byte body)");
            } else {
                sb.append(" (Content-Type = ").append(contentType.toString())
                        .append(",binary ").append(requestBody.contentLength()).append("-byte body omitted)");
            }
            sb.append("]");
            Log.d(TAG, String.format(Locale.getDefault(), "%s %s", method, sb.toString()));
        }

        Log.d(TAG, "heard ---" + request.headers().toString());
        okhttp3.Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        //the response time
        Log.d(TAG, String.format(Locale.getDefault(), "Received response for [url = %s] in %.1fms", url, (t2 - t1) / 1e6d));

        //the response state
        Log.d(TAG, String.format(Locale.CHINA, "Received response is %s ,message[%s],code[%d]", response.isSuccessful() ? "success" : "fail", response.message(), response.code()));

        //the response data
        ResponseBody body = response.body();

        BufferedSource source = body.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
        Buffer buffer = source.buffer();
        Charset charset = Charset.defaultCharset();
        MediaType contentType = body.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }
        String bodyString = buffer.clone().readString(charset);
        Log.d(TAG, String.format("Received response json string [%s]", bodyString));

        return response;
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

}
