package edu.feicui.newsday.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.Activity.AddActivity;
import edu.feicui.newsday.Adapter.HomeInfoAdapter;
import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.SaveMessage;


/**
 * Created by 太上老君 on 2016/7/10.
 */
public class Home_info extends Fragment implements View.OnClickListener{
    View view;
    ViewPager viewPager;
    TabLayout tab;
    List<Fragment> list_fra;
    Fragment_hot fragment_hot;
    RelativeLayout add_rl;
    ImageView add_iv;
    SaveMessage saveMessage;
    List<String> tablist ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment_info,null);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getview();
        setview();
    }

    private void getview(){
        viewPager = (ViewPager) view.findViewById(R.id.info_vp);
        tab = (TabLayout) view.findViewById(R.id.info_tab);
        add_rl = (RelativeLayout) view.findViewById(R.id.home_add_rl);
        add_iv = (ImageView) view.findViewById(R.id.home_add_iv);

        list_fra = new ArrayList<Fragment>();

        saveMessage = new SaveMessage(getActivity());
    }
    private void setview(){
        getMessage();
        if(tablist.size()!=0){
            for(int i = 0;i<tablist.size();i++){
                fragment_hot = new Fragment_hot();
                Bundle bundle = new Bundle();
                bundle.putString("newType",tablist.get(i));
                fragment_hot.setArguments(bundle);
                list_fra.add(fragment_hot);
            }
        }
        HomeInfoAdapter homeInfoAdapter = new HomeInfoAdapter(getActivity().getSupportFragmentManager(),list_fra,getActivity());
        viewPager.setAdapter(homeInfoAdapter);
        viewPager.setOffscreenPageLimit(tablist.size() - 1);
        //        将tablayout和ViewPager关联起来
        tab.setupWithViewPager(viewPager);
        add_rl.setOnClickListener(this);
}

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_add_rl:
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getMessage(){
        tablist = saveMessage.addActivityload();
    }
}
