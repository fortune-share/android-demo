package com.example.xuanfu.ui.model;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.HashMap;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
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

    /**
     * 发起支付
     */
    private void initiatePayment() {
        HashMap map = new HashMap();
        map.put("appId", "cc721b17b6c0411ea2c0dd2a1862b031");
        map.put("amount", 100);
        map.put("body", "商品描述");
        map.put("detail", "商品详情");
        map.put("attach", "附加数据");
        map.put("id", "3094374632884632784");
        map.put("currency", "CNY");
        map.put("notify_url", "7673874");
        map.put("goodId", "6236237");

        String json = new Gson().toJson(map);

//            EasyLog.Companion.getDEFAULT().e(json);

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }

}
