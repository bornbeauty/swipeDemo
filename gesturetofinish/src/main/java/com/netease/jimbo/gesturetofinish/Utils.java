package com.netease.jimbo.gesturetofinish;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * description:
 *
 * @author jimbo zhongjinbao1994@gmail.com
 * @since 2016/8/22 15:43
 */
public class Utils {

    public static int getAbsoluteDisplayMetricsWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        return dm.widthPixels;
    }

    public static int getAbsoluteDisplayMetricsHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);

        return dm.heightPixels;
    }

}
