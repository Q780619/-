package edu.feicui.newsday.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.SaveMessage;


/**
 * Created by 太上老君 on 2016/7/17.
 */
public class AboutUsActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getview();
        setview();
    }

    public void setLayout() {
        setContentView(R.layout.aboutus);
    }

    public void getview() {
        toolbar = (Toolbar) findViewById(R.id.aboutus_toolabr);
    }

    public void setview() {
        toolbar.setTitle("NewsDays");
        loadMessage();

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.aboutus_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public void loadMessage(){
        SaveMessage save = new SaveMessage(AboutUsActivity.this);
        int color = save.colorload(); //将提取到的共享参数赋值给boo
        if(color!=0){
            toolbar.setBackgroundResource(color);
        }
    }



    @Override
    public void onClick(View v) {

    }
}
