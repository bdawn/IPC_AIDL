package com.example.lugx.moreprocess.service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {

    AIDLManager aidlManager = new AIDLManager();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        int check = checkCallingOrSelfPermission("com.example.lugx.moreprocess.SERVICE");
        if (check == PackageManager.PERMISSION_DENIED){
            return null;
        }
        return aidlManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
