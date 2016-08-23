package com.netease.jimbo.gesturetofinish;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

/**
 * description:
 *
 * @author jimbo zhongjinbao1994@gmail.com
 * @since 2016/8/22 15:49
 */
public class SwipeActivity extends AppCompatActivity implements SwipeListener {

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSwipeView();
    }

    private void addSwipeView() {
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        ViewGroup contentView = (ViewGroup) decorView.getChildAt(0);
        SwipeFrameLayout swipeView = new SwipeFrameLayout(this);

        ViewGroup.LayoutParams contentViewParams = contentView.getLayoutParams();
        ViewGroup.LayoutParams swipeViewParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        decorView.removeView(contentView);
        swipeView.addView(contentView, swipeViewParams);
        decorView.addView(swipeView, contentViewParams);

        swipeView.setClickable(true);
        swipeView.setSwipeListener(this);
    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    public void onEdgeLeftSlide() {
        finishFromLeftToRight();
    }

    @Override
    public void onEdgeRightSlide() {
        finishFromRightToLeft();
    }

    private void finishFromLeftToRight() {
        finish();
        overridePendingTransition(-1, R.anim.base_slide_right_out);
    }

    private void finishFromRightToLeft() {
        finish();
        overridePendingTransition(-1, R.anim.base_slide_left_out);
    }
}
