package com.archzues.mytest.eventdispatch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by acercow on 18-6-6.
 */

public class TouchLinearLayout extends LinearLayout implements View.OnTouchListener, View.OnClickListener {
    public TouchLinearLayout(Context context) {
        super(context);
        init();
    }

    public TouchLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        $Log.w(this, "=====<init>=====");
//        setOnTouchListener(this);
//        setOnClickListener(this);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        $Log.d(this, "onInterceptTouchEvent -> " + ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        $Log.d(this, "dispatchTouchEvent -> " + ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        $Log.d(this, "onTouchEvent -> " + event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        $Log.d(this, "onTouch -> " + event);
        return false;
    }

    @Override
    public void onClick(View v) {
        $Log.d(this, "onClick -> " + v);
    }
}
