package com.jinfukeji.internetindustrialpark.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jinfukeji.internetindustrialpark.R;
import com.jinfukeji.internetindustrialpark.been.ZhaoshangXiangmuBeen;

import java.util.ArrayList;

/**
 * Created by "于志渊"
 * 时间:"16:03"
 * 包名:com.jinfukeji.internetindustrialpark.adapter
 * 描述:招商项目适配器
 */

public class ZhaoshangXiangmuAdapyer extends BaseAdapter{
    private ArrayList<ZhaoshangXiangmuBeen.MessageBean> messageBeen;
    private Context context;

    public ZhaoshangXiangmuAdapyer(ArrayList<ZhaoshangXiangmuBeen.MessageBean> messageBeen, Context context) {
        if (messageBeen == null){
            messageBeen=new ArrayList<>();
        }else {
            this.messageBeen = messageBeen;
        }
        this.context = context;
    }

    @Override
    public int getCount() {
        if (messageBeen != null){
            return messageBeen.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
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
        viewHolder holder;
        if (view == null){
            holder=new viewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.item_zsxm,null);
            holder.zsxm_item_name_tv= (TextView) view.findViewById(R.id.zsxm_item_name_tv);
            view.setTag(holder);
        }else {
            holder= (viewHolder) view.getTag();
        }
        ZhaoshangXiangmuBeen.MessageBean messageBean= (ZhaoshangXiangmuBeen.MessageBean) getItem(i);
        holder.zsxm_item_name_tv.setText(messageBean.getPname());
        return view;
    }

    public class viewHolder{
        TextView zsxm_item_name_tv;
    }
}
