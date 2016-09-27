package com.example.lugx.moreprocess;

import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lugx.aidl.IComputePlus;
import com.example.lugx.aidl.IUserManager;
import com.example.lugx.aidl.User;

public class ComputeActivity extends AppCompatActivity {

    public static final String TAG = "ComputeActivity";

    IComputePlus computePlus;

    BinderPool binderPool;

    EditText etA,etB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        new Thread(){
            @Override
            public void run() {
                super.run();
                binderPool = BinderPool.getInstance();
            }
        }.start();

        etA = (EditText) findViewById(R.id.et_a);
        etB = (EditText) findViewById(R.id.et_b);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        if (binderPool == null){
            return;
        }
        switch (view.getId()) {
            case R.id.bt_result:
                if (computePlus == null){
                    IBinder binder = binderPool.getBinder(BinderPool.COMPUTE_PLUS);
                    computePlus = IComputePlus.Stub.asInterface(binder);
                }
                int a = Integer.valueOf(etA.getText().toString());
                int b = Integer.valueOf(etB.getText().toString());
                try {
                    Log.i(TAG,"a+b=" + computePlus.plus(a,b));
                    ((TextView)findViewById(R.id.tv_result))
                            .setText(String.valueOf(computePlus.plus(a,b)));
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;

        }
    }
}
