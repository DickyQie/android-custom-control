package com.zhangqie.customcontrol.demo4;

import android.content.Context;

/**
 * Created by zhangqie on 2018/5/3.
 */

public class Util {

    /**
     * dip转换px
     */
    public static int dip2px(int dip, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转换dip
     */

    public static int px2dip(int px, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

}
