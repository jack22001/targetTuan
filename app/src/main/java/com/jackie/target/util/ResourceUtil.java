package com.jackie.target.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.jackie.target.config.GlobalConfig;


public class ResourceUtil {
    public static Resources getResources() {
        return GlobalConfig.getAppContext().getResources();
    }


    public static int getDimenPixSize(int id) {
        return getResources().getDimensionPixelSize(id);
    }

    public static int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, getResources().getDisplayMetrics());
    }

    public static float dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return dp * scale + 0.5f;
    }

    public static float sp2px(Context context, float sp) {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static float sp2px(float sp) {
        final float scale = getResources().getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(dp2px(l), dp2px(t), dp2px(r), dp2px(b));
            v.requestLayout();
        }
    }

    public static void setViewWidth(View view, int width, int height){
        ViewGroup.LayoutParams lp;
        lp=view.getLayoutParams();
        if(width!=0){
            lp.width=dp2px(width);
        }else {
            lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        if(height!=0){
            lp.height=dp2px(height);
        }else {
            lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        }
        view.setLayoutParams(lp);
    }


    /**
     * If the color is Dark, make it lighter and vice versa
     *
     * @param color in int,
     * @param factor The factor greater than 0.0 and smaller than 1.0
     * @return int
     */
    public static int manipulateColorBrightness(int color, float factor) {
        int a = Color.alpha(color);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
//        if (r + b + g < 128 * 3) factor = 1 / factor;// check if the color is bright or dark
//        r = Math.round(r * factor);
//        b = Math.round(b * factor);
//        g = Math.round(g * factor);
        if (r > 127) r = 255 - Math.round((255 - r) * factor);
        if (g > 127) g = 255 - Math.round((255 - g) * factor);
        if (b > 127) b = 255 - Math.round((255 - b) * factor);

        return Color.argb(a,
                Math.min(r, 255),
                Math.min(g, 255),
                Math.min(b, 255)
        );
    }
}
