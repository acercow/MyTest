package com.archzues.mytest.eventdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by jansen on 2018/7/4.
 */

public class MyViewPager extends ViewGroup {
    private int mLastInterceptX;
    private int mLastInterceptY;

    private int mLastX;
    private int mLastY;
    private Scroller mScroller;

    private int mPagerWidth;

    private int mIndex;



    public MyViewPager(Context context) {
        super(context);
        init();
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
        }
        mPagerWidth = getChildAt(childCount - 1).getRight();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();

        boolean intercept = false;
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                break;

            case MotionEvent.ACTION_MOVE:
                if (Math.abs(x - mLastInterceptX) > ViewConfiguration.get(getContext()).getScaledTouchSlop()) {
                    intercept = true;
                }
                mLastX = x;
                break;
        }
        mLastInterceptX = x;
        mLastInterceptY = y;
        return intercept;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        int deltaX = mLastX - x;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:
                if (getScrollX() + getWidth() + deltaX > mPagerWidth) {
                    scrollTo((getChildCount() - 1) * getWidth(), 0);
                } else if (getScrollX() + deltaX < 0) {
                    scrollTo(0, 0);
                } else {
                    scrollBy(deltaX, 0);
//                    scrollTo(getScrollX() + deltaX, 0);
                }
                break;

            case MotionEvent.ACTION_UP:
                int index = (getScrollX() + getWidth() / 2) / getWidth();
                mScroller.startScroll(getScrollX(), 0, index * getWidth() - getScrollX(), 0);
                invalidate();
                break;

        }
        mLastX = x;
        mLastY = y;
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}
