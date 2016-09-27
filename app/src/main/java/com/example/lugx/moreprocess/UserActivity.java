package com.example.lugx.moreprocess;

import android.app.Activity;
import android.content.Intent;
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

public class UserActivity extends Activity {
    public static final String TAG = UserActivity.class.getName();
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
                binderPool = BinderPool.getInstance();
            }
        }.start();

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
            case R.id.btn_nex_activity:
                Intent it = new Intent(UserActivity.this,ComputeActivity.class);
                startActivity(it);
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
