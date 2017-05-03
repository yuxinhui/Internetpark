package com.jinfukeji.internetindustrialpark;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jinfukeji.internetindustrialpark.activity.ZhaoshangXiangmuActivity;
import com.jinfukeji.internetindustrialpark.adapter.MainAdapter;
import com.jinfukeji.internetindustrialpark.been.MainBeen;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REFRESH_COMPLETE = 0X110;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int[] colors=new int[]{android.R.color.holo_blue_bright, android.R.color.holo_green_light
            , android.R.color.holo_orange_light, android.R.color.holo_red_light};
    private MainAdapter mainAdapter;
    private MainBeen mainBeen=new MainBeen();
    private ArrayList<MainBeen.MessageBean> messageBeen=new ArrayList<>();
    private String url_main=InternetApplication.getUrl()+"category/query";
    //轮播图控件
    private int[] imgsId;
    private LinearLayout lunbotu_dots_ll;
    private ViewPager lunbotu_guide_vp;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            lunbotu_guide_vp.setCurrentItem(lunbotu_guide_vp.getCurrentItem()+1,true);
            handler.sendEmptyMessageDelayed(0,2000);
        }
    };

    //下拉刷新
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case REFRESH_COMPLETE:
                    initData();
                    mSwipeRefreshLayout.setRefreshing(false);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initBanner();//轮播图
    }

    //请求数据
    private void initData() {
        RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
        StringRequest request=new StringRequest(Request.Method.POST, url_main,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Log.e("url_main",url_main);
                            Gson gson=new Gson();
                            mainBeen=gson.fromJson(s,MainBeen.class);
                            if ("ok".equals(mainBeen.getStatus())){
                                messageBeen.clear();
                                messageBeen.addAll(mainBeen.getMessage());
                                mainAdapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(MainActivity.this,"暂无数据",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(MainActivity.this,"请检查网络配置",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void initView() {
        mSwipeRefreshLayout= (SwipeRefreshLayout) this.findViewById(R.id.main_swipe_rl);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE,2000);
            }
        });
        mSwipeRefreshLayout.setColorScheme(colors);
        GridView main_gv = (GridView) this.findViewById(R.id.main_gv);
        mainAdapter=new MainAdapter(messageBeen,MainActivity.this);
        main_gv.setAdapter(mainAdapter);
        main_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(MainActivity.this, ZhaoshangXiangmuActivity.class);
                SharedPreferences sp=getSharedPreferences("picAndname", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("name",messageBeen.get(i).getName());
                ed.putString("pic",messageBeen.get(i).getPic());
                ed.putString("content",messageBeen.get(i).getDescription());
                ed.apply();
                startActivity(intent);
                finish();
            }
        });
    }

    //轮播图
    private void initBanner() {
        imgsId=new int[]{R.mipmap.jiameng_banner_01,R.mipmap.jiameng_banner_02,R.mipmap.jiameng_banner_03};
        lunbotu_dots_ll= (LinearLayout) this.findViewById(R.id.lunbotu_dots_ll);
        initDot();//动态加点
        lunbotu_guide_vp= (ViewPager) this.findViewById(R.id.lunbotu_guide_vp);
        lunbotu_guide_vp.setAdapter(new MyBannerAdapter());
        lunbotu_guide_vp.setCurrentItem(imgsId.length*10000);
        updateDescAndDot();//根据当前page来显示不同的文字和点
        handler.sendEmptyMessageDelayed(0,2000);
        lunbotu_guide_vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        handler.removeCallbacksAndMessages(null);
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        handler.sendEmptyMessageDelayed(0,3000);
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.sendEmptyMessageDelayed(0,3000);
                        break;
                }
                return false;
            }
        });
        lunbotu_guide_vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateDescAndDot();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //根据当前page来显示不同的文字和点
    private void updateDescAndDot() {
        int currentPage=lunbotu_guide_vp.getCurrentItem()%imgsId.length;
        //更新点
        //遍历所有的点，当点的位置和currentPage相当的时候，则设置为可用，否则是禁用
        for (int i=0;i<lunbotu_dots_ll.getChildCount();i++){
            lunbotu_dots_ll.getChildAt(i).setEnabled(i==currentPage);
        }
    }

    //动态加点
    private void initDot() {
        for (int i=0;i<imgsId.length;i++){
            View view=new View(MainActivity.this);
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(20,20);
            lp.leftMargin=(i==0?0:20);//给除了第一个点之外的点都加上marginLeft
            view.setLayoutParams(lp);
            view.setBackgroundResource(R.drawable.banner_select_dot);
            lunbotu_dots_ll.addView(view);
        }
    }

    //双击退出程序
    private long ExitTime=0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if (System.currentTimeMillis()-ExitTime > 2000){
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                ExitTime=System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    //轮播图适配器
    private class MyBannerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view== object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView=new ImageView(MainActivity.this);
            imageView.setImageResource(imgsId[position%imgsId.length]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
