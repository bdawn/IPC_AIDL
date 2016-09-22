package com.example.lugx.moreprocess;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lugx.aidl.IComputePlus;
import com.example.lugx.aidl.IUserManager;
import com.example.lugx.aidl.User;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();
    IUserManager userManager;
    IComputePlus computePlus;

    BinderPool binderPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(){
            @Override
            public void run() {
                super.run();
                binderPool = BinderPool.getInstance(MainActivity.this);
            }
        }.start();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (binderPool == null){
                    return;
                }
                if (userManager == null){
                    IBinder binder = binderPool.getBinder(BinderPool.USER_MANAGER);
                    userManager = IUserManager.Stub.asInterface(binder);
                }
                try {
                    userManager.addUser(new User("bb","aa"));

                    Log.i(TAG,"User size:" + userManager.getUsers().size());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                if (computePlus == null){
                    IBinder binder = binderPool.getBinder(BinderPool.COMPUTE_PLUS);
                    computePlus = IComputePlus.Stub.asInterface(binder);
                }
                try {
                    Log.i(TAG,"1+2=" + computePlus.plus(1,2));
                    Log.i(TAG,"user.name" + computePlus.getUserName(new User("11","Tom")));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
