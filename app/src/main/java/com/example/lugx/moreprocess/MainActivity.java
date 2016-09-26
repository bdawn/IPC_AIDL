package com.example.lugx.moreprocess;

import android.app.Activity;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lugx.aidl.IComputePlus;
import com.example.lugx.aidl.IUserManager;
import com.example.lugx.aidl.User;

public class MainActivity extends Activity {
    public static final String TAG = MainActivity.class.getName();
    IUserManager userManager;
    IComputePlus computePlus;

    BinderPool binderPool;

    EditText etA,etB;

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

        etA = (EditText) findViewById(R.id.et_a);
        etB = (EditText) findViewById(R.id.et_b);
    }

    public void onClick(View view) {
        if (binderPool == null){
            return;
        }
        switch (view.getId()) {
            case R.id.bt_add_new_user:
                if (userManager == null){
                    IBinder binder = binderPool.getBinder(BinderPool.USER_MANAGER);
                    userManager = IUserManager.Stub.asInterface(binder);
                }
                try {
                    userManager.addUser(new User("1001","Jack"));

                    Log.i(TAG,"User size:" + userManager.getUsers().size());
                    ((TextView)findViewById(R.id.tv_user_size))
                            .setText(String.valueOf(userManager.getUsers().size()));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.bt_result:
                if (computePlus == null){
                    IBinder binder = binderPool.getBinder(BinderPool.COMPUTE_PLUS);
                    computePlus = IComputePlus.Stub.asInterface(binder);
                }
                int a = Integer.valueOf(etA.getText().toString());
                int b = Integer.valueOf(etB.getText().toString());
                try {
                    Log.i(TAG,"1+2=" + computePlus.plus(1,2));
                    ((TextView)findViewById(R.id.tv_result))
                            .setText(String.valueOf(computePlus.plus(a,b)));
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
