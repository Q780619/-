package edu.feicui.newsday.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.Activity.WelcomeActivity;
import edu.feicui.newsday.Adapter.ViewPagerAdapter;
import edu.feicui.newsday.Base.BaseActivity;
import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.SaveMessage;


/**
 * Created by 太上老君 on 2016/7/10.
 */
public class LeadActivity extends BaseActivity {
    ViewPager viewPager;
    Button button;
    List<View> list;
    LayoutInflater layoutInflater;
    boolean isThreePage ;
    ViewPagerAdapter viewPagerAdapter;
    int position=-1;
    @Override
    public void setLayout() {
        getMessage();
        setContentView(R.layout.lead);
        saveMessage();
        isInfoTransmit();
    }

    @Override
    public void getview() {
        viewPager = (ViewPager) findViewById(R.id.lead_vp);
        button = (Button) findViewById(R.id.lead_skip_bt);
    }

    @Override
    public void setview() {
        setImg();
        isThreePage = false;
        viewPagerAdapter = new ViewPagerAdapter(list,LeadActivity.this);
        viewPager.setAdapter(viewPagerAdapter);
        button.setOnClickListener(this);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (viewPager.getCurrentItem() == (list.size() - 1)) {
                    button.setVisibility(View.VISIBLE);
                } else {
                    button.setVisibility(View.GONE);
                }
                switch (state) {
                    case 0:
                        if (!isThreePage &&(viewPager.getCurrentItem() == list.size() - 1)) {
                            Intent intent = new Intent(LeadActivity.this, WelcomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        isThreePage = true;
                        break;
                    case 1:
                        isThreePage = false;
                        break;
                    case 2:
                        isThreePage = true;
                        break;
                }

            }
        });
    }
    private void setImg(){
//        Student student = Student.getInstance();
//        student.setTitle("1111");
//        String str =student.getTitle();
        list = new ArrayList<View>();
        layoutInflater = getLayoutInflater().from(this);
        ImageView imageView1 = (ImageView) layoutInflater.inflate(R.layout.lead1,null);
        ImageView imageView2 = (ImageView) layoutInflater.inflate(R.layout.lead2,null);
        ImageView imageView3 = (ImageView) layoutInflater.inflate(R.layout.lead3,null);
        list.add(imageView1);
        list.add(imageView2);
        list.add(imageView3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            /**
             * 直接跳转监听
             */
            case R.id.lead_skip_bt:
                if(position == -1){
                    Intent intent = new Intent(LeadActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }

                break;
        }
    }

    public void isInfoTransmit(){
        Intent i=this.getIntent();
        if(i!=null){
            Bundle b=i.getExtras();
            if(b!=null){
                position=b.getInt("number");
            }
        }
    }

    /**
     * 共享参数，获取数据，用于判断是否为第一次使用
     */
    private void getMessage(){
        SaveMessage save = new SaveMessage(LeadActivity.this);
        String boo = save.statusload(); //将提取到的共享参数赋值给boo
        if(boo.equals("false")){         //false表示不是第一次登录
            Intent intent = new Intent(LeadActivity.this,WelcomeActivity.class);
            startActivity(intent);
            finish();
        }
    }
    /**
     * 保存共享参数，用于判断是否首次使用
     */
    private void saveMessage(){
        SaveMessage sm = new SaveMessage(LeadActivity.this);
        sm.statussave("false");
    }
}
