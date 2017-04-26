package com.jinfukeji.internetindustrialpark.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jinfukeji.internetindustrialpark.InternetApplication;
import com.jinfukeji.internetindustrialpark.MainActivity;
import com.jinfukeji.internetindustrialpark.R;
import com.jinfukeji.internetindustrialpark.adapter.ZhaoshangXiangmuAdapyer;
import com.jinfukeji.internetindustrialpark.been.ZhaoshangXiangmuBeen;
import com.jinfukeji.internetindustrialpark.utils.RequestManager;

import java.util.ArrayList;

/**
 * Created by "于志渊"
 * 时间:"10:20"
 * 包名:com.jinfukeji.internetindustrialpark.activity
 * 描述:招商项目界面
 */

public class ZhaoshangXiangmuActivity extends AppCompatActivity{
    private String name,img,content;
    private LinearLayout zsxm_fanhui_ll;
    private TextView zsxm_name_tv,zsxm_content_tv;
    private NetworkImageView zsxm_img_banner;
    private GridView zsxm_banner_gv;
    ZhaoshangXiangmuAdapyer xiangmuAdapyer;
    ArrayList<ZhaoshangXiangmuBeen.MessageBean> messageBeen=new ArrayList<>();
    ZhaoshangXiangmuBeen xiangmuBeen=new ZhaoshangXiangmuBeen();
    String url_zsxm;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zsxm);
        SharedPreferences sp=getSharedPreferences("picAndname", Context.MODE_PRIVATE);
        name=sp.getString("name","");
        img=sp.getString("pic","");
        content=sp.getString("content","");
        url_zsxm= InternetApplication.getUrl()+"project/query?name="+name;
        Log.e("name",url_zsxm);
        initData();
        initView();//初始化控件
        initOnclick();//点击事件
    }

    private void initData() {
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url_zsxm,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s != null){
                            Gson gson=new Gson();
                            xiangmuBeen=gson.fromJson(s,ZhaoshangXiangmuBeen.class);
                            if ("ok".equals(xiangmuBeen.getStatus())){
                                ArrayList<ZhaoshangXiangmuBeen.MessageBean> been= (ArrayList<ZhaoshangXiangmuBeen.MessageBean>) xiangmuBeen.getMessage();
                                messageBeen.clear();
                                messageBeen.addAll(been);
                                xiangmuAdapyer.notifyDataSetChanged();
                            }else {
                                Toast.makeText(ZhaoshangXiangmuActivity.this,"暂时没有数据",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ZhaoshangXiangmuActivity.this,"请检查网络连接",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    //点击事件
    private void initOnclick() {
        zsxm_fanhui_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ZhaoshangXiangmuActivity.this, MainActivity.class));
                finish();
            }
        });
        zsxm_banner_gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ZhaoshangXiangmuActivity.this,PersonalInformation.class);
                intent.putExtra("project",messageBeen.get(i).getPname());
                startActivity(intent);
                finish();
            }
        });
    }

    //初始化控件
    private void initView() {
        zsxm_fanhui_ll= (LinearLayout) this.findViewById(R.id.zsxm_fanhui_ll);

        zsxm_name_tv= (TextView) this.findViewById(R.id.zsxm_name_tv);
        zsxm_name_tv.setText(name);

        zsxm_img_banner= (NetworkImageView) this.findViewById(R.id.zsxm_img_banner);
        RequestManager.init1(getApplicationContext());
        zsxm_img_banner.setImageUrl(img,RequestManager.getImageLoader());

        zsxm_content_tv= (TextView) this.findViewById(R.id.zsxm_content_tv);
        zsxm_content_tv.setText(content);

        zsxm_banner_gv= (GridView) this.findViewById(R.id.zsxm_banner_gv);
        xiangmuAdapyer=new ZhaoshangXiangmuAdapyer(messageBeen,this);
        zsxm_banner_gv.setAdapter(xiangmuAdapyer);
    }

    //双击退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            startActivity(new Intent(ZhaoshangXiangmuActivity.this, MainActivity.class));
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
