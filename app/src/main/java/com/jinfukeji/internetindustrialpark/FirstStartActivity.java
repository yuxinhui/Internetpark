package com.jinfukeji.internetindustrialpark;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.jinfukeji.internetindustrialpark.yindaotu.GuideActivity;

/**
 * Created by "于志渊"
 * 时间:"11:43"
 * 包名:com.jinfukeji.internetindustrialpark
 * 描述:判断是否第一次启动
 */

public class FirstStartActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean mFirst=isFirstEnter(this,this.getClass().getName());
        if (mFirst){
            handler.sendEmptyMessageAtTime(switch_guideactivity,1000);
        }else {
            handler.sendEmptyMessageAtTime(switch_splashactivity,1000);
        }
    }

    // 判断应用是否初次加载，读取SharedPreferences中的guide_activity字段
    private static final String SHAREDPREFERENCES_NAME="activity_splash";
    private static final String KEY_GUIDE_ACTIVITY ="activity_guide";
    private boolean isFirstEnter(Context context, String name) {
        if (context==null || name==null || "".equalsIgnoreCase(name)){
            return false;
        }
        String mResultStr = context.getSharedPreferences(SHAREDPREFERENCES_NAME, Context.MODE_PRIVATE).getString(KEY_GUIDE_ACTIVITY, "");
        if (mResultStr.equalsIgnoreCase("false")){
            return false;
        }else {
            return true;
        }
    }

    //Handler:跳转至不同页面
    public static final int switch_splashactivity = 1000;
    public static final int switch_guideactivity = 1001;
    public Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case switch_splashactivity:
                    Intent intent=new Intent(FirstStartActivity.this,SplashActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case switch_guideactivity:
                    Intent intent1=new Intent(FirstStartActivity.this,GuideActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
            }
            super.handleMessage(msg);
        }
    };
}
