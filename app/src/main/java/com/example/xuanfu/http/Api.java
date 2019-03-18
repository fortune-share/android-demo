package com.example.xuanfu.http;


import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * @author wangmojiang
 */
public interface Api {

    @POST("/preparePayOrder")
    Observable<Void> preparePayOrder(@Body RequestBody requestBody);

}