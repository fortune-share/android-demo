package com.example.xuanfu.http;


import com.example.xuanfu.entity.PrepaidOrderEntity;
import com.example.xuanfu.entity.ResultEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * @author wangmojiang
 */
public interface Api {
    /**
     * 创建预支付订单
     *
     * @param requestBody
     * @return
     */
    @POST("/preparePayOrder")
    Observable<PrepaidOrderEntity> preparePayOrder(@Body RequestBody requestBody);

    /**
     * 查询订单
     *
     * @param requestBody
     * @return
     */
    @POST("/payOrderStatus")
    Observable<ResultEntity> checkingOrder(@Body RequestBody requestBody);

}