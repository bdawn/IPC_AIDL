package com.example.lugx.moreprocess.service.iInterface;

import android.os.RemoteException;

import com.example.lugx.aidl.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by gxlu on 2016/9/22.
 */
public class IUserManagerImpl extends com.example.lugx.aidl.IUserManager.Stub {

    List<User> users = new ArrayList<>();
    @Override
    public void addUser(User user) throws RemoteException {
        users.add(user);
    }

    @Override
    public List<User> getUsers() throws RemoteException {
        return users;
    }

}
