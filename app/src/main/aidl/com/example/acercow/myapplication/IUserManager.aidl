// IUserManagerAidlInterface.aidl
package com.example.acercow.myapplication;
import com.example.acercow.myapplication.bean.User;
import com.example.acercow.myapplication.IOnNewFellowListener;
// Declare any non-default types here with import statements

interface IUserManager {
    void addUser(in User user);
    List<User> getUsers();

    void registerListener(IOnNewFellowListener listener);
    void unRegisterListener(IOnNewFellowListener listener);
}
