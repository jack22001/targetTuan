package com.jackie.target.config;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;


/**
 * @author liuxu34@wanda.cn (Liu Xu)
 */
public class GlobalConfig {
    private static final String CLIENT_VERSION = "client_version";
    private static Context appContext;
    private static boolean mainProcessInited = false;
    private static String rootDir = "wanda";
    private static boolean debug = false;
    private static boolean sIsUpgrade;
    private static String DETECT_WEBGL = "webglDetect";
    private static String SUPPORT_WEBGL = "webglSupport";
    public static int SUPPORT_WEBGL_VALUE = 1;
    public static int NO_SUPPORT_WEBGL_VALUE = 2;
    public static int INIT_SUPPORT_WEBGL_VALUE = 0;



    public static void setAppContext(Context context) {
        appContext = context;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public static boolean isMainProcessInited() {
        return mainProcessInited;
    }

    public static void mainProcessInited(boolean mainProcessInited) {
        GlobalConfig.mainProcessInited = mainProcessInited;
    }

    public static void setAppRootDir(String dir) {
        rootDir = dir;
    }

    public static String getAppRootDir() {
        return rootDir;
    }

    /**
     * This should only be called in main android project, like in Application,
     * because in Gradle build system the BuildConfig.DEBUG in library project is always false.
     *
     * @param debug true if in debug mode, false otherwise.
     */
    public static void setDebug(boolean debug) {
        GlobalConfig.debug = debug;
    }

    /**
     * Because in Gradle build system the BuildConfig.DEBUG in library project is always false,
     * and this will not be fixed in short-term, so we need to use this instead of
     * BuildConfig.DEBUG in library project.
     *
     * @return true if in debug mode, false otherwise.
     */
    public static boolean isDebug() {
        return debug;
    }

    public static int getVersionCode() {
        try {
            PackageInfo packageInfo = appContext.getPackageManager().getPackageInfo(appContext
                    .getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getVersionName() {
        try {
            PackageInfo packageInfo = appContext.getPackageManager().getPackageInfo(appContext
                    .getPackageName(), PackageManager.GET_ACTIVITIES);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

//    public static void initClientVersion() {
//        int versionCode = getVersionCode();
//        int oldVersion = PreferenceHelper.getInstance().getInt(CLIENT_VERSION, 0);
//        PreferenceHelper.getInstance().saveInt(CLIENT_VERSION, versionCode);
//        sIsUpgrade = (oldVersion != versionCode);
//    }

    public static boolean isUpgrade() {
        return sIsUpgrade;
    }
//
//    // 获取页面加载流程统计开关
//    public static boolean getRecordLaunchFlowSwitch() {
//        return PreferenceHelper.getBooleanValue("debug_config", "pref_key_skip_souce", true);
//    }

//    // 获取页面信息显示开关
//    public static boolean getDisplayPageInfoSwitch() {
//        return PreferenceHelper.getBooleanValue("debug_config", "pref_key_page_info", false);
//    }

    /**
     * support = 0 默认初始化，
     * support = 1 支持webgl
     * support = 2 不支持webgl
     * @param support
     */
//    public static void saveSupportWebGL(int support) {
//        PreferenceHelper.getInstance().saveInt(SUPPORT_WEBGL, support);
//    }
//
//    public static int isSupportWebGL() {
//        return PreferenceHelper.getInstance().getInt(SUPPORT_WEBGL, 0);
//    }

//    public static void setNotSupportWebGLPhone(){
//        if (Build.MODEL.contains("Redmi")) {
//            GlobalConfig.saveSupportWebGL(NO_SUPPORT_WEBGL_VALUE);
//        }
//        if (Build.MODEL.contains("vivo")) { //vivo X5M  vivo Y51A
//            GlobalConfig.saveSupportWebGL(NO_SUPPORT_WEBGL_VALUE);
//        }
//        if (Build.MODEL.contains("MX5")) {
//            GlobalConfig.saveSupportWebGL(NO_SUPPORT_WEBGL_VALUE);
//        }
//    }
}