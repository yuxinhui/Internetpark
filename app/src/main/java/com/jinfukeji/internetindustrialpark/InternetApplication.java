package com.jinfukeji.internetindustrialpark;

import android.app.Application;

/**
 * Created by "于志渊"
 * 时间:"11:40"
 * 包名:com.jinfukeji.internetindustrialpark
 * 描述:存放全局变量
 */

public class InternetApplication extends Application{
    public static InternetApplication instace;
    public static final String url="http://114.55.142.212:8080/zhaoshang/";

    @Override
    public void onCreate() {
        super.onCreate();
        instace=this;
    }

    public static InternetApplication getInstace() {
        return instace;
    }

    public static void setInstace(InternetApplication instace) {
        InternetApplication.instace = instace;
    }

    public static String getUrl() {
        return url;
    }
}
