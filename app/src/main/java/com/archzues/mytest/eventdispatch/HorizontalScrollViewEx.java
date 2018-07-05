package com.archzues.mytest.eventdispatch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by jansen on 2018/7/3.
 */

public class HorizontalScrollViewEx extends ViewGroup {

    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;

    private int mLastX;
    private int mLastY;

    private int mLastInterceptX;
    private int mLastInterceptY;

    private int mChildWidth;
    private int mChildCount;
    private int mChildIndex;

    private static final String TAG = HorizontalScrollViewEx.class.getSimpleName();


    public HorizontalScrollViewEx(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mScroller = new Scroller(getContext());
        mVelocityTracker = VelocityTracker.obtain();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mChildCount = getChildCount();
        for (int i = 0; i < mChildCount; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mChildWidth = getChildAt(0).getMeasuredWidth();
        for (int i = 0; i < mChildCount; i++) {
            View childView = getChildAt(i);
            childView.layout(i * mChildWidth, 0, (i + 1) * mChildWidth, childView.getMeasuredHeight());
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                    intercept = true;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastInterceptX;
                int deltaY = y - mLastInterceptY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercept = true;
                } else {
                    intercept = false;
                }
                break;

            case MotionEvent.ACTION_UP:
                intercept = false;
                break;

            default:
                break;

        }
        Log.d(TAG, "intercept -> " + intercept);

        mLastInterceptX = x;
        mLastInterceptY = y;
        mLastX = x;
        mLastY = y;

        return intercept;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (getScrollX() - deltaX < 0) {
//                    scrollTo(0, 0);
                } else if (getScrollX() - deltaX > (mChildCount - 1) * mChildWidth) {
//                    scrollTo((mChildCount - 1) * mChildWidth, 0);
                } else {
                    scrollBy(-deltaX, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                int scollX = getScrollX();
                mVelocityTracker.computeCurrentVelocity(1000);
                float velocityX = mVelocityTracker.getXVelocity();
                Log.i(TAG, "velocity -> " + velocityX);
                if (Math.abs(velocityX) > 50) {
                    mChildIndex = velocityX > 0 ? mChildIndex - 1 : mChildIndex + 1;
                    Log.i(TAG, "By velocity -> " + mChildIndex);
                } else {
                    mChildIndex = (scollX + mChildWidth / 2) / mChildWidth;
                    Log.i(TAG, "Not By velocity");
                }

                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildCount - 1));
                int scrollDeltaX = mChildIndex * mChildWidth - scollX;
                mScroller.startScroll(scollX, 0, scrollDeltaX, 0);
                invalidate();
                break;

            default:
                break;

        }
        mLastX = x;
        mLastY = y;

        return true;
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
