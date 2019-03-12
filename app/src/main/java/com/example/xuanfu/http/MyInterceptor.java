package com.example.xuanfu.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author： 淘跑
 * @Date: 2018/7/5 11:43
 * @Use：
 */

public class MyInterceptor implements Interceptor {
    private Map<String, String> headers=new HashMap<>();
    private boolean isNeedAuthorization=true;

    public MyInterceptor(Map<String, String> headers) {
        this.headers = headers;
    }

    public MyInterceptor(boolean isNeedAuthorization) {
        this.isNeedAuthorization = isNeedAuthorization;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder();
        Request request = chain.request();
        String time = System.currentTimeMillis() + "";
        String url = request.url().toString();

        headers.put("Content-Type","application/json");
        headers.put("Accept-Language","zh-Hans-CN");
        headers.put("X-Timestamp",time);

        if (headers != null && headers.size() > 0) {
            Set<String> keys = headers.keySet();
            for (String headerKey : keys) {
                builder.addHeader(headerKey, headers.get(headerKey)).build();
            }
        }

        //请求信息
        return chain.proceed(builder.build());
    }

}