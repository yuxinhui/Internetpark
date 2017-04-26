package com.jinfukeji.internetindustrialpark.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.jinfukeji.internetindustrialpark.InternetApplication;
import com.jinfukeji.internetindustrialpark.R;
import com.jinfukeji.internetindustrialpark.been.PersonalDataBeen;

/**
 * Created by "于志渊"
 * 时间:"16:54"
 * 包名:com.jinfukeji.internetindustrialpark.activity
 * 描述:个人信息界面
 */

public class PersonalInformation extends AppCompatActivity{
    private String project,url_personal;
    private LinearLayout personal_fanhui_ll;
    private TextView personal_ok_tv;
    private EditText name_et,phone_et,qqorwechat_et,sex_et,nianling_et,liuyan_et;
    private String name,phone,qq,sex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaldata);
        Intent intent=getIntent();
        project=intent.getStringExtra("project");
        initView();//初始化控件
        initData();//获取输入的数据
        initOnclick();//点击事件
    }

    //点击事件
    private void initOnclick() {
        personal_fanhui_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonalInformation.this,ZhaoshangXiangmuActivity.class));
                finish();
            }
        });
        personal_ok_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
                if (TextUtils.isEmpty(name)){
                    name_et.requestFocus();
                    name_et.setError("名字不能为空");
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    phone_et.requestFocus();
                    phone_et.setError("手机号不能为空");
                    return;
                }
                if (!(phone.length()==11) || !phone.startsWith("1")){
                    phone_et.requestFocus();
                    phone_et.setError("请填写正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(sex)){
                    sex_et.requestFocus();
                    sex_et.setError("性别不能为空");
                    return;
                }
                if ("男".equals(sex)){
                    sex_et.setText("0");
                    sex=sex_et.getText().toString();
                    return;
                }
                if ("女".equals(sex)){
                    sex_et.setText("1");
                    sex=sex_et.getText().toString();
                    return;
                }
                if (TextUtils.isEmpty(qq)){
                    qqorwechat_et.requestFocus();
                    qqorwechat_et.setError("QQ号不能为空");
                    return;
                }
                url_personal= InternetApplication.getUrl()+"info/addInfo?name="+name+"&gender="+sex+"&telephone="+
                phone+"&qq="+qq+"&project="+project;
                Log.e("url_personal",url_personal);
                personalDate(url_personal);//提交数据
                //finish();
            }
        });
    }

    //数据提交
    private void personalDate(String url_personal) {
        RequestQueue queue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(Request.Method.POST, url_personal,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if(s != null){
                            Gson gson=new Gson();
                            PersonalDataBeen dataBeen=gson.fromJson(s,PersonalDataBeen.class);
                            if ("ok".equals(dataBeen.getStatus())){
                                Toast.makeText(PersonalInformation.this,"信息提交成功",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(PersonalInformation.this,"信息提交失败",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(PersonalInformation.this,"请检查网络连接",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    //获取输入的数据
    private void initData() {
        name=name_et.getText().toString();
        phone=phone_et.getText().toString();
        qq=qqorwechat_et.getText().toString();
        sex=sex_et.getText().toString();
    }

    //初始化控件
    private void initView() {
        personal_fanhui_ll= (LinearLayout) this.findViewById(R.id.personal_fanhui_ll);
        personal_ok_tv= (TextView) this.findViewById(R.id.personal_ok_tv);
        name_et= (EditText) this.findViewById(R.id.personal_name_et);
        phone_et= (EditText) this.findViewById(R.id.personal_phone_et);
        qqorwechat_et= (EditText) this.findViewById(R.id.personal_qqorwechat_et);
        sex_et= (EditText) this.findViewById(R.id.personal_sex_et);
        nianling_et= (EditText) this.findViewById(R.id.personal_nianling_et);
        liuyan_et= (EditText) this.findViewById(R.id.personal_liuyan_et);
    }
}
