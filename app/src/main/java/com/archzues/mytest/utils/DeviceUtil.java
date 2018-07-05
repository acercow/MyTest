package com.archzues.mytest.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by jansen on 2018/7/5.
 */

public class DeviceUtil {

    public static int getScreenWidth(Context context) {
        return getScreenSpec(context).widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return getScreenSpec(context).heightPixels;
    }


    public static DisplayMetrics getScreenSpec(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }
}
