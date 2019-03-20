package com.example.xuanfu.ui.model;

import android.app.Application;
import android.support.annotation.NonNull;

import com.example.xuanfu.entity.PrepaidOrderEntity;
import com.example.xuanfu.entity.ResultEntity;
import com.example.xuanfu.http.Api;
import com.example.xuanfu.http.RetrofitClient;
import com.example.xuanfu.util.SignatureUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.http.ResponseThrowable;
import me.goldze.mvvmhabit.utils.RxUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainViewModel extends BaseViewModel {
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public BindingCommand pay = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //发起支付
            initiatePayment();
        }
    });
    public BindingCommand check = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //查询订单
            checkingOrder();
        }
    });

    private String id = "";

    /**
     * 查询订单
     */
    private void checkingOrder() {
        HashMap<String, String> map = new HashMap<>(2);
        map.put("appId", "cc721b17b6c0411ea2c0dd2a1862b031");
        map.put("id", id);

        try {
            Map map1 = SignatureUtils.signWith(map, SignatureUtils.getPrivateKeyFromPem(getApplication().getBaseContext()));

            String json = new Gson().toJson(map1);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
            RetrofitClient.getInstance().create(Api.class)
                    .checkingOrder(requestBody)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                    .compose(RxUtils.schedulersTransformer())
                    .compose(RxUtils.exceptionTransformer())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            showDialog("正在请求...");
                        }
                    })
                    .subscribe(new Consumer<ResultEntity>() {
                        @Override
                        public void accept(ResultEntity resultEntity) throws Exception {

                        }
                    }, new Consumer<ResponseThrowable>() {
                        @Override
                        public void accept(ResponseThrowable throwable) throws Exception {
                            dismissDialog();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                            dismissDialog();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发起支付
     */
    private void initiatePayment() {
        HashMap<String, java.io.Serializable> map = new HashMap<>(9);
        map.put("appId", "cc721b17b6c0411ea2c0dd2a1862b031");
        map.put("amount", 100);
        map.put("body", "商品描述");
        map.put("detail", "商品详情");
        map.put("attach", "附加数据");
        id = String.valueOf(System.currentTimeMillis());
        map.put("id", id);
        map.put("currency", "CNY");
        map.put("notify_url", "7673874");
        map.put("goodId", "6236237");

        try {
            Map map1 = SignatureUtils.signWith(map, SignatureUtils.getPrivateKeyFromPem(getApplication().getBaseContext()));
            String json = new Gson().toJson(map1);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);


            RetrofitClient.getInstance().create(Api.class)
                    .preparePayOrder(requestBody)
                    .compose(RxUtils.bindToLifecycle(getLifecycleProvider()))
                    .compose(RxUtils.schedulersTransformer())
                    .compose(RxUtils.exceptionTransformer())
                    .doOnSubscribe(new Consumer<Disposable>() {
                        @Override
                        public void accept(Disposable disposable) throws Exception {
                            showDialog("正在请求...");
                        }
                    })
                    .subscribe(new Consumer<PrepaidOrderEntity>() {
                        @Override
                        public void accept(PrepaidOrderEntity prepaidOrderEntity) throws Exception {

                        }
                    }, new Consumer<ResponseThrowable>() {
                        @Override
                        public void accept(ResponseThrowable throwable) throws Exception {
                            dismissDialog();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {
                            dismissDialog();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}