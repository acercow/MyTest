package com.archzues.mytest.eventdispatch;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by acercow on 18-6-6.
 */

public class TouchButton extends AppCompatButton implements View.OnTouchListener, View.OnClickListener {
    public TouchButton(Context context) {
        super(context);
        init();
    }

    public TouchButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TouchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        $Log.w(this, "=====<init>=====");
//        setOnTouchListener(this);
//        setOnClickListener(this);
//        setEnabled(false);
//        setClickable(true);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;

            case MotionEvent.ACTION_MOVE:
//                getParent().requestDisallowInterceptTouchEvent(true);
                break;

            default:
                break;

        }
        $Log.v(this, "dispatchTouchEvent -> " + event.getAction());
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        $Log.v(this, "onTouchEvent -> " + event.getAction());
//        return super.onTouchEvent(event);
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        $Log.v(this, "onTouch -> " + event.getAction());
        return false;
    }

    @Override
    public void onClick(View v) {
        $Log.v(this, "onClick -> " + v);
    }
}
