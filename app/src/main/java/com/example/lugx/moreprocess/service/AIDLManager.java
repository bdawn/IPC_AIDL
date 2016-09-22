package com.example.lugx.moreprocess.service;

import android.os.IBinder;
import android.os.RemoteException;

import com.example.lugx.aidl.IAIDLManager;
import com.example.lugx.moreprocess.BinderPool;
import com.example.lugx.moreprocess.service.iInterface.IComputePlus;
import com.example.lugx.moreprocess.service.iInterface.IUserManager;

/**
 * aidl接口管理器
 * Created by gxlu on 2016/9/22.
 */
public class AIDLManager extends IAIDLManager.Stub {


    @Override
    public IBinder getBinder(int binderCode) throws RemoteException {
        switch (binderCode){
            case BinderPool.COMPUTE_PLUS:
                return new IComputePlus();
            case BinderPool.USER_MANAGER:
                return new IUserManager();
        }
        return null;
    }
}
