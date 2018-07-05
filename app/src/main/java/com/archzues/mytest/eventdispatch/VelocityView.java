package com.archzues.mytest.eventdispatch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by jansen on 2018/7/4.
 */

public class VelocityView extends View {

    private VelocityTracker mVelocityTracker;

    private int mLastX;
    private int mLastY;

    public VelocityView(@NonNull Context context) {
        super(context);
        init();
    }

    public VelocityView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public VelocityView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker = VelocityTracker.obtain();
        mVelocityTracker.addMovement(event);
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        $Log.i(this, "event -> " + event.getAction());
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mVelocityTracker.computeCurrentVelocity(100);
                float vX = mVelocityTracker.getXVelocity();
                float vY = mVelocityTracker.getYVelocity();

                $Log.i(this, "x -> " + vX + "  y -> " + vY);


                setTranslationX(getTranslationX() + x - mLastX);
                setTranslationY(getTranslationY() + y - mLastY);
                break;

            case MotionEvent.ACTION_UP:
                mVelocityTracker.clear();
                mVelocityTracker.recycle();
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }
}
