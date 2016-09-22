package com.example.lugx.moreprocess.service.iInterface;

import android.os.RemoteException;

import com.example.lugx.aidl.OnNewUserComeListener;
import com.example.lugx.aidl.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gxlu on 2016/9/22.
 */
public class IUserManager extends com.example.lugx.aidl.IUserManager.Stub {

    List<User> users = new ArrayList<>();
    @Override
    public void addUser(User user) throws RemoteException {
        users.add(user);
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        users.add(new User("aa","bb"));
        return users;
    }

    @Override
    public void registerListener(OnNewUserComeListener listener) throws RemoteException {

//            listenerList.add(listener);
    }

    @Override
    public void unRegisterListener(OnNewUserComeListener listener) throws RemoteException {
//        listeners.unregister(listener);

//            listenerList.remove(listener);

    }
}
