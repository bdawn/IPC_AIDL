package com.example.lugx.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 *
 * Created by gxlu on 2016/8/26.
 */
public class User implements Parcelable {

    public String userName;

    public String userId;

    public User(String userId,String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public User(Parcel in) {
        userId = in.readString();

        userName = in.readString();
    }

    public String toString(){
        return userId + "\n" + userName;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public void readFromParcel(Parcel in){
        userName = in.readString();
        userId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(userName);
    }
}
