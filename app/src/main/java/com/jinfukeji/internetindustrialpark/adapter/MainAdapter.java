package com.jinfukeji.internetindustrialpark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.jinfukeji.internetindustrialpark.R;
import com.jinfukeji.internetindustrialpark.been.MainBeen;
import com.jinfukeji.internetindustrialpark.utils.RequestManager;

import java.util.ArrayList;

/**
 * Created by "于志渊"
 * 时间:"15:48"
 * 包名:com.jinfukeji.internetindustrialpark.adapter
 * 描述:主页适配器
 */

public class MainAdapter extends BaseAdapter{
    private ArrayList<MainBeen.MessageBean> messageBeen;
    private Context context;

    public MainAdapter(ArrayList<MainBeen.MessageBean> messageBeen, Context context) {
        this.context = context;
        if (messageBeen == null){
            this.messageBeen=new ArrayList<>();
        }else {
            this.messageBeen = messageBeen;
        }
    }

    @Override
    public int getCount() {
        if (messageBeen != null){
            return messageBeen.size();
        }
        return 0;
    }

    @Override
    public MainBeen.MessageBean getItem(int i) {
        if (messageBeen != null){
            return messageBeen.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        viewHolder viewHolder;
        if (view == null){
            viewHolder=new viewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_main,null);
            viewHolder.main_been_img= (NetworkImageView) view.findViewById(R.id.main_been_img);
            viewHolder.main_been_description= (TextView) view.findViewById(R.id.main_been_description);
            viewHolder.main_been_name= (TextView) view.findViewById(R.id.main_been_name);
            viewHolder.main_small_icon= (NetworkImageView) view.findViewById(R.id.main_small_icon);
            view.setTag(viewHolder);
        }else {
            viewHolder= (MainAdapter.viewHolder) view.getTag();
        }
        MainBeen.MessageBean bean=getItem(i);
        RequestManager.init(context);
        viewHolder.main_been_img.setImageUrl(bean.getPic(),RequestManager.getImageLoader());
        viewHolder.main_been_name.setText(bean.getName());
        viewHolder.main_been_description.setText(bean.getDescription());
        viewHolder.main_small_icon.setImageUrl(bean.getIco(),RequestManager.getImageLoader());
        return view;
    }

    //存放控件
    public class viewHolder{
        NetworkImageView main_been_img,main_small_icon;
        TextView main_been_description,main_been_name;
    }
}
