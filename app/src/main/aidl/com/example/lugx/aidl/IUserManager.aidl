// IUserManager.aidl
package com.example.lugx.aidl;

import com.example.lugx.aidl.User;
import com.example.lugx.aidl.OnNewUserComeListener;

// Declare any non-default types here with import statements

interface IUserManager {

    void addUser(in User user);
    List<User> getUsers();

    void registerListener(in OnNewUserComeListener listener);
    void unRegisterListener(in OnNewUserComeListener listener);
}
