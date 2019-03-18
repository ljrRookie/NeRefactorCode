package com.netease.nerefactorcode;

import android.app.Application;

public class NEApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        HttpHelper.init(new VolleyProcessor(this));
        HttpHelper.init(new OkHttpProcessor());
    }
}
