package com.example.lugx.moreprocess.service.iInterface;

import android.os.RemoteException;

import com.example.lugx.aidl.User;

/**
 * Created by gxlu on 2016/9/22.
 */
public class IComputePlus extends com.example.lugx.aidl.IComputePlus.Stub {

    @Override
    public int plus(int a, int b) throws RemoteException {

        return a+b;
    }

    @Override
    public String getUserName(User user) throws RemoteException {
        return user.userName;
    }
}
