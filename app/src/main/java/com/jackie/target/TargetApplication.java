package com.jackie.target;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.jackie.target.config.GlobalConfig;

import java.util.ArrayList;
import java.util.List;

public class TargetApplication extends Application {
    private static Context mContext;
    private static TargetApplication mApplication;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mApplication = this;
        GlobalConfig.setAppContext(mContext);

    }

    private static List<Activity> lists = new ArrayList<>();

    public static void addActivity(Activity activity) {
        lists.add(activity);
    }

    public static void clearActivity() {
        if (lists != null) {
            for (Activity activity : lists) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
            lists.clear();
        }
    }

}
