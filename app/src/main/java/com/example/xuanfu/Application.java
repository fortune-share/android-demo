package com.example.xuanfu;


import android.content.Context;

import com.haoge.easyandroid.EasyAndroid;

public class Application extends android.app.Application {

    public static Context getContext() {
        return mContext;
    }

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        EasyAndroid.init(mContext);
    }
}
