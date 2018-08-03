package com.archzues.mytest;

import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * Created by acercow on 18-8-3.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("ipc", "MyApplication: " + Process.myPid());
    }
}
