package edu.feicui.newsday.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 滑动适配器
 * Created by 太上老君 on 2016/7/10.
 */
public class ViewPagerAdapter extends PagerAdapter {
    List<View> viewlist ;
    Context context;

    public ViewPagerAdapter(List<View> viewlist, Context context) {
        this.viewlist = viewlist;
        this.context = context;
    }
    @Override
    public int getCount() {
        return viewlist.size();
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = viewlist.get(position);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = viewlist.get(position);
        container.removeView(view);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
