package com.netease.jimbo.gesturetofinish;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * description:
 *
 * @author jimbo zhongjinbao1994@gmail.com
 * @since 2016/8/22 14:53
 */
public class SwipeFrameLayout extends FrameLayout {

    private static final int ORIGIN_X = 0;
    private static final int ORIGIN_Y = 0;

    private static int SCREEN_WIDTH;
    private static int EDGE_RANGE = 50;         // 距离边缘的大小
    private static int SLIDE_RANGE_X;           // 左右滑动限定值

    private SwipeListener mListener;

    private float mMoveLeft;

    private ViewDragHelper mViewDragHelper;

    public SwipeFrameLayout(Context context) {
        super(context);
        initDate();
        initViewDrag();
    }

    private void initDate() {
        SCREEN_WIDTH = Utils.getAbsoluteDisplayMetricsWidth(getContext());
        SLIDE_RANGE_X = SCREEN_WIDTH / 3;
    }

    public SwipeFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDate();
        initViewDrag();
    }

    public SwipeFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDate();
        initViewDrag();
    }

    public void setSwipeListener(SwipeListener listener) {
        mListener = listener;
    }

    private void initViewDrag() {
        mViewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                return left;
            }

            @Override
            public void onViewReleased(View releasedChild, float x, float y) {
                if (mMoveLeft > SLIDE_RANGE_X) {
                    mListener.onEdgeLeftSlide();
                    return;
                }  else if (mMoveLeft < -SLIDE_RANGE_X) {
                    mListener.onEdgeRightSlide();
                    return;
                }
                mViewDragHelper.settleCapturedViewAt(ORIGIN_X, ORIGIN_Y);
                invalidate();
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                mMoveLeft = left;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mViewDragHelper.cancel();
                break;
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                if (x < EDGE_RANGE || x > (SCREEN_WIDTH - EDGE_RANGE)) {
                    // 当手势发生在屏幕边缘的时候才将事件交付viewDragHelper去处理
                    return mViewDragHelper.shouldInterceptTouchEvent(event);
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (x < EDGE_RANGE || x > (SCREEN_WIDTH - EDGE_RANGE)) {
                    mViewDragHelper.processTouchEvent(event);
                    return true;
                }
                break;

            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                mViewDragHelper.processTouchEvent(event);
                break;
        }
        return false;
    }

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }
}
