package com.example.lugx.moreprocess;

import android.app.Application;

/**
 *
 * Created by gxlu on 2016/9/27.
 */
public class MyApplication extends Application {

    private static MyApplication instance;

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = MyApplication.this;
    }
}
