// IOnNewFellowListener.aidl
package com.example.acercow.myapplication;
import com.example.acercow.myapplication.bean.User;
// Declare any non-default types here with import statements

interface IOnNewFellowListener {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    void onNewFellowArrived(in User user);
}
