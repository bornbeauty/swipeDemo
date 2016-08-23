package com.netease.jimbo.swipedemo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

/**
 * description:
 *
 * @author jimbo zhongjinbao1994@gmail.com
 * @since 2016/8/23 9:33
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {
                Log.d("activity", activity.getClass().getSimpleName() + "Created");
            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.d("activity", activity.getClass().getSimpleName() + "Started");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.d("activity", activity.getClass().getSimpleName() + "Resumed");
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.d("activity", activity.getClass().getSimpleName() + "Paused");
            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.d("activity", activity.getClass().getSimpleName() + "Stopped");
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.d("activity", activity.getClass().getSimpleName() + "Destroyed");
            }
        });

    }
}
