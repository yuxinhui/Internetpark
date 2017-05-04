package com.jinfukeji.internetindustrialpark.yindaotu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jinfukeji.internetindustrialpark.R;
import com.jinfukeji.internetindustrialpark.SplashActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by "于志渊"
 * 时间:"11:46"
 * 包名:com.jinfukeji.internetindustrialpark.yindaotu
 * 描述:第一次启动引导图
 */

public class GuideActivity extends AppCompatActivity implements OnPageChangeListener{
    //实例化图片资源
    ViewPager vp;
    private int[] imageIdArray=new int[]{R.mipmap.yindaotu1,R.mipmap.yindaotu2,R.mipmap.yindaotu3};//图片资源的数组
    private List<View> viewList;//图片资源的集合

    //最后一页的按钮
    private Button ib_start;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ib_start= (Button) this.findViewById(R.id.guide_ib_start);
        ib_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置已经引导
                setGuide();
                //跳转
                startActivity(new Intent(GuideActivity.this,SplashActivity.class));
                finish();
            }
        });
        //加载ViewPager
        initViewPager();
    }

    // 加载图片ViewPager
    private void initViewPager() {
        vp = (ViewPager) findViewById(R.id.guide_vp);
        viewList = new ArrayList<View>();
        //获取一个Layout参数，设置为全屏
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //循环创建View并加入到集合中
        int len = imageIdArray.length;
        for (int i = 0;i<len;i++){
            //new ImageView并设置全屏和图片资源
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setBackgroundResource(imageIdArray[i]);

            //将ImageView加入到集合中
            viewList.add(imageView);
        }
        //View集合初始化好后，设置Adapter
        vp.setAdapter(new GuidePageAdapter(viewList));
        //设置滑动监听
        vp.setOnPageChangeListener(this);
    }

    //设置已经引导
    private static final String SHAREDPREFERENCES_NAME="activity_splash";
    private static final String KEY_GUIDE_ACTIVITY ="activity_guide";
    private void setGuide() {
        SharedPreferences settings=getSharedPreferences(SHAREDPREFERENCES_NAME,0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(KEY_GUIDE_ACTIVITY, "false");
        editor.apply();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    //滑动后的监听
    @Override
    public void onPageSelected(int position) {
        //判断是否是最后一页，若是则显示按钮
        if (position == imageIdArray.length - 1){
            ib_start.setVisibility(View.VISIBLE);
        }else {
            ib_start.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
