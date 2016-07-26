package edu.feicui.newsday.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.Utils.SaveMessage;


/**
 * Created by 太上老君 on 2016/7/10.
 */
public class HomeInfoAdapter extends FragmentStatePagerAdapter {
    List<Fragment> list;
    List<String> tablist;
    Context context;
    public HomeInfoAdapter(FragmentManager fm, List<Fragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
        tablist = new ArrayList<String>();
        SaveMessage saveMessage = new SaveMessage(context);
        tablist = saveMessage.addActivityload();
    }

    @Override
    public Fragment getItem(int position) {
        return (list == null||list.size() == 0)?null:list.get(position);
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position);
    }
}
