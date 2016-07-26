package edu.feicui.newsday.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import edu.feicui.newsday.R;
import edu.feicui.newsday.entity.MyCollectInfo;

/**
 * Created by 太上老君 on 2016/7/24.
 */
public class HomeFindAdapter extends BaseAdapter {
    List<MyCollectInfo> list;
    Context context;

    public HomeFindAdapter(List<MyCollectInfo> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder vh= null;
        if(view==null){
            vh = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.home_finditem,null);
            vh.textView = (TextView) view.findViewById(R.id.find_tv);
            view.setTag(vh);
        }else{
            vh = (ViewHolder) view.getTag();
        }
        String info = list.get(position).getTitle();
        vh.textView.setText(info);
        return view;
    }
    class ViewHolder{
        TextView textView;
    }
}
