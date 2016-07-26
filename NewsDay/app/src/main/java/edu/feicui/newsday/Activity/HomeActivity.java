package edu.feicui.newsday.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import edu.feicui.newsday.Fragment.Home_find;
import edu.feicui.newsday.Fragment.Home_hot;
import edu.feicui.newsday.Fragment.Home_info;
import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.SaveMessage;
import edu.feicui.newsday.Utils.Tools_Pager;


/**
 * Created by 太上老君 on 2016/7/8.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageView mycollection_iv,aboutus_iv,set_iv,home_info_iv,home_hot_iv,home_find_iv,home_login;
    TextView mycollection_tv,aboutus_tv,set_tv,home_info_tv,home_hot_tv,home_find_tv;
    View mycollection,aboutus,set,home_info,home_hot,home_find;
    FragmentManager fm;
    FragmentTransaction ft;

    Home_info info;
    Home_hot hot;
    Home_find find;

    OnekeyShare oks;

    Intent intent4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        showShare();
        getview();
        setview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMessage();
    }

    private void getview(){
        toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.home_drawer);

        home_login = (ImageView) findViewById(R.id.home_login);
        mycollection = findViewById(R.id.home_drawer_mycollect);
        aboutus = findViewById(R.id.home_drawer_aboutus);
        set = findViewById(R.id.home_drawer_set);

        mycollection_iv = (ImageView) mycollection.findViewById(R.id.iv);
        mycollection_tv = (TextView) mycollection.findViewById(R.id.tv);

        aboutus_iv = (ImageView) aboutus.findViewById(R.id.iv);
        aboutus_tv = (TextView) aboutus.findViewById(R.id.tv);

        set_iv = (ImageView) set.findViewById(R.id.iv);
        set_tv = (TextView) set.findViewById(R.id.tv);

        home_info = findViewById(R.id.home_information);
        home_hot = findViewById(R.id.home_hot);
        home_find = findViewById(R.id.home_find);

        home_info_iv = (ImageView) home_info.findViewById(R.id.typebar_iv);
        home_info_tv = (TextView) home_info.findViewById(R.id.typebar_tv);
        home_hot_iv = (ImageView) home_hot.findViewById(R.id.typebar_iv);
        home_hot_tv = (TextView) home_hot.findViewById(R.id.typebar_tv);
        home_find_iv = (ImageView) home_find.findViewById(R.id.typebar_iv);
        home_find_tv = (TextView) home_find.findViewById(R.id.typebar_tv);
    }

    private void setview(){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        info = new Home_info();
        hot = new Home_hot();
        find = new Home_find();
        setDrawer();
        setType();
        setFragment();
        toolbar.setTitle("NewsDay");
        toolbar.setNavigationIcon(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);//建立Acticity与Toolbar连接（依附）
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_share:
                        oks.show(HomeActivity.this);
                        Toast.makeText(HomeActivity.this, "我启动了", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();//保持左边按钮与抽屉同步
        drawerLayout.setDrawerListener(actionBarDrawerToggle);//抽屉与左边图片绑定

        home_info.setOnClickListener(this);
        home_hot.setOnClickListener(this);
        home_find.setOnClickListener(this);
        home_login.setOnClickListener(this);
        aboutus.setOnClickListener(this);
        mycollection.setOnClickListener(this);
        set.setOnClickListener(this);
    }
    private void setFragment(){
        ft.add(R.id.home_fragment_rl, info).add(R.id.home_fragment_rl, hot)
                .add(R.id.home_fragment_rl, find);
        ft.show(info).hide(hot).hide(find);
        ft.commit();
    }

    private void setDrawer(){
        mycollection_iv.setImageResource(R.mipmap.drawer_mycollection);
        mycollection_tv.setText("我的收藏");
        aboutus_iv.setImageResource(R.mipmap.drawer_aboutus);
        aboutus_tv.setText("关于我们");
        set_iv.setImageResource(R.mipmap.drawer_set);
        set_tv.setText("设置");
    }
    private void setType(){
        Tools_Pager.setPager(home_info, R.mipmap.new_selected, R.mipmap.new_unselected, "资讯", true);
        Tools_Pager.setPager(home_hot, R.mipmap.hot_selected, R.mipmap.hot_unselected, "热点", false);
        Tools_Pager.setPager(home_find, R.mipmap.find_selected, R.mipmap.find_unselect, "搜索", false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);//加载menu菜单
        return true;
    }

    @Override
    public void onClick(View v) {
        ft = fm.beginTransaction();
        switch (v.getId()){
            case R.id.home_information:
                Tools_Pager.setPager(home_info, R.mipmap.new_selected, R.mipmap.new_unselected, "资讯", true);
                Tools_Pager.setPager(home_hot, R.mipmap.hot_selected, R.mipmap.hot_unselected, "热点", false);
                Tools_Pager.setPager(home_find, R.mipmap.find_selected, R.mipmap.find_unselect, "搜索", false);
                ft.show(info).hide(hot).hide(find);
                break;
            case R.id.home_hot:
                Tools_Pager.setPager(home_info, R.mipmap.new_selected, R.mipmap.new_unselected, "资讯", false);
                Tools_Pager.setPager(home_hot, R.mipmap.hot_selected, R.mipmap.hot_unselected, "热点", true);
                Tools_Pager.setPager(home_find, R.mipmap.find_selected, R.mipmap.find_unselect, "搜索", false);
                ft.hide(info).show(hot).hide(find);
                break;
            case R.id.home_find:
                Tools_Pager.setPager(home_info, R.mipmap.new_selected, R.mipmap.new_unselected, "资讯", false);
                Tools_Pager.setPager(home_hot, R.mipmap.hot_selected, R.mipmap.hot_unselected, "热点", false);
                Tools_Pager.setPager(home_find, R.mipmap.find_selected, R.mipmap.find_unselect, "搜索", true);
                ft.hide(info).hide(hot).show(find);
                break;
            case R.id.home_login:
                Intent intent2 = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent2);
                break;
            case R.id.home_drawer_aboutus:
                Intent intent = new Intent(HomeActivity.this,AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.home_drawer_mycollect:
                Intent intent3 = new Intent(HomeActivity.this,MyCollectActivity.class);
                startActivity(intent3);
                break;
            case R.id.home_drawer_set:
                intent4 = new Intent(HomeActivity.this,SetActivity.class);
                startActivity(intent4);
                break;
        }
        ft.commit();
    }
    private void showShare() {
        ShareSDK.initSDK(this);
        oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.app_name));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    private void loadMessage(){
        SaveMessage save = new SaveMessage(HomeActivity.this);
        int color = save.colorload(); //将提取到的共享参数赋值给boo
        if(color!=0){
            toolbar.setBackgroundResource(color);
        }
    }
}
