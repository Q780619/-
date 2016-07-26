package edu.feicui.GitDroid.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.feicui.GitDroid.Adapter.SplashPagerAdapter;
import edu.feicui.GitDroid.R;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by 太上老君 on 2016/7/27.
 */
public class SplashPagerFragment extends Fragment {
    View view;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;
//    @Bind(R.id.ivPhoneBlank)
    ImageView ivPhoneBlank;
    @Bind(R.id.ivPhoneFont)
    ImageView ivPhoneFont;
    @Bind(R.id.layoutPhone)
    FrameLayout layoutPhone;
    @Bind(R.id.content)
    FrameLayout content;

    SplashPagerAdapter sa;
    List<View> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_splash_pager, container, false);
        ButterKnife.bind(this, view);
        setView();
        return view;
    }
    private void setView(){
        list = new ArrayList<View>();
        list.add(new Fraone(getContext()));
        list.add(new Fratwo(getContext()));
        list.add(new Frathree(getContext()));

        sa = new SplashPagerAdapter(list,getActivity());
        viewPager.setAdapter(sa);
        indicator.setViewPager(viewPager);

        viewPager.addOnPageChangeListener(pageChangeListener);
        viewPager.addOnPageChangeListener(phoneViewListener);
    }

    /**
     * 背景动画
     */
    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 手机动画
     */
    private ViewPager.OnPageChangeListener phoneViewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
