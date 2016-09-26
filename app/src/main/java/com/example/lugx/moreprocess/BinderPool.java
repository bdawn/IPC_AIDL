package com.example.lugx.moreprocess;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.lugx.aidl.IAIDLManager;
import com.example.lugx.moreprocess.service.MyService;

import java.util.concurrent.CountDownLatch;

/**
 * Binder连接池
 * Created by gxlu on 2016/9/21.
 */
public class BinderPool {

    public static final int USER_MANAGER = 0;
    public static final int COMPUTE_PLUS = 1;
    private static final String TAG = "BinderPool";

    private static BinderPool instance;
    IAIDLManager aidlManager;

    //同步处理
    private CountDownLatch mCountDownLatch;

    Context context;


    private BinderPool(Context context){
        this.context = context;
        bindService();

    }

    public static BinderPool getInstance(Context context){
        if (instance == null){
            instance = new BinderPool(context);
        }

        return instance;
    }

    private synchronized void bindService(){
        mCountDownLatch = new CountDownLatch(1);
        Intent intent = new Intent();
        intent.setClass(context,MyService.class);
        context.bindService(intent,mConnection, Context.BIND_AUTO_CREATE);

        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public IBinder getBinder(int code){
        IBinder iBinder = null;
        try {
            if (aidlManager != null){
                iBinder = aidlManager.getBinder(code);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return iBinder;
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlManager = IAIDLManager.Stub.asInterface(service);
            try {
                aidlManager.asBinder().linkToDeath(mBinderPoolDeathRecipient,0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 如果service异常退出，进行重连
     */
    private IBinder.DeathRecipient mBinderPoolDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.w(TAG,"binderDied");
            aidlManager.asBinder().unlinkToDeath(mBinderPoolDeathRecipient,0);
            aidlManager = null;
            bindService();
        }
    };

}
