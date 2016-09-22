// IUserManager.aidl
package com.example.lugx.aidl;

import com.example.lugx.aidl.User;

// Declare any non-default types here with import statements

interface IUserManager {

    void addUser(in User user);
    List<User> getUsers();
}
