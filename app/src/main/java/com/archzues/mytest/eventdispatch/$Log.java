package com.archzues.mytest.eventdispatch;

import android.util.Log;
import android.widget.TextView;

/**
 * Created by acercow on 18-6-6.
 */

public class $Log {

    public static final String TAG = "Jansen";
    public static TextView tvText;


    public static void v(Object o, String content) {
        Log.v(TAG, "[" + o.getClass().getSimpleName() + "] " + content);
        tvLog(o, content);
    }

    public static void d(Object o, String content) {
        Log.d(TAG, "[" + o.getClass().getSimpleName() + "] " + content);
        tvLog(o, content);
    }

    public static void i(Object o, String content) {
        Log.i(TAG, "[" + o.getClass().getSimpleName() + "] " + content);
        tvLog(o, content);
    }

    public static void w(Object o, String content) {
        Log.w(TAG, "[" + o.getClass().getSimpleName() + "] " + content);
        tvLog(o, content);
    }

    public static void e(Object o, String content) {
        Log.e(TAG, "[" + o.getClass().getSimpleName() + "] " + content);
        tvLog(o, content);
    }

    private static void tvLog(Object o, String content) {
        if (tvText != null) {
            tvText.append("[" + o.getClass().getSimpleName() + "] " + content + "\n");
        }
    }
}
