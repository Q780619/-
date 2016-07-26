package edu.feicui.newsday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.Adapter.SetRecyclerViewAdapter;
import edu.feicui.newsday.R;
import edu.feicui.newsday.Server.SetServer;
import edu.feicui.newsday.Utils.SaveMessage;

/**
 * Created by 太上老君 on 2016/7/21.
 */
public class SetActivity extends AppCompatActivity implements View.OnClickListener{
    Button start_bt,pause_bt,stop_bt;
    Intent intent;
    ToggleButton toggleButton;
    RecyclerView recyclerView;
    List<Integer> colorlist;
    SetRecyclerViewAdapter recyclerViewAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        setContentView(R.layout.set);
    }

    public void getview() {
        start_bt = (Button) findViewById(R.id.set_go);
        pause_bt = (Button) findViewById(R.id.set_pause);
        stop_bt = (Button) findViewById(R.id.set_stop);
        toggleButton = (ToggleButton) findViewById(R.id.set_isopen_tb);
        recyclerView = (RecyclerView) findViewById(R.id.set_selectcolor_rv);
        toolbar = (Toolbar) findViewById(R.id.set_toolbar);
        colorlist = new ArrayList<Integer>();
        loadMessage();
    }

    public void setview() {
        setcolorlist();//设置图片颜色集合
        toolbar.setTitle("Newsday");
        loadMe();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.aboutus_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        intent = new Intent(SetActivity.this, SetServer.class);
        startService(intent);
        start_bt.setOnClickListener(this);
        pause_bt.setOnClickListener(this);
        stop_bt.setOnClickListener(this);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    saveMessage("true");
                } else {
                    saveMessage("false");
                }
            }
        });
        recyclerViewAdapter = new SetRecyclerViewAdapter(colorlist,SetActivity.this);
        recyclerView.setLayoutManager(new GridLayoutManager(SetActivity.this, 8));
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setRecycleViewClickListener(new SetRecyclerViewAdapter.RecycleViewClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                saveMe(colorlist.get(position));
                for (int i = 0;i<colorlist.size();i++){
                    Log.i("list",colorlist.get(position)+"    \n");
                }
                loadMe();
            }

            @Override
            public void OnItemLongClickListener(View view, int position) {

            }
        });
    }
    public void saveMessage(String str){
        SaveMessage sm = new SaveMessage(SetActivity.this);
        sm.tzsave(str);
    }
    public void loadMessage(){
        SaveMessage save = new SaveMessage(SetActivity.this);
        String boo = save.tzload(); //将提取到的共享参数赋值给boo
        if(boo.equals("true")){
            toggleButton.setChecked(true);
        }else{
            toggleButton.setChecked(false);
        }
    }

    /**
     * 设置颜色集合
     * @param
     */
    private void setcolorlist(){
        colorlist.add(R.color.colorfirst);
        colorlist.add(R.color.colorsencond);
        colorlist.add(R.color.colorthird);
        colorlist.add(R.color.colorfourth);
        colorlist.add(R.color.colorfifth);
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.set_go:
                bundle.putInt("state",1);
                break;
            case R.id.set_pause:
                bundle.putInt("state",2);
                break;
            case R.id.set_stop:
                bundle.putInt("state",3);
                break;
        }
        intent.putExtras(bundle);
        startService(intent);
    }
    public void saveMe(int color){
        SaveMessage sm = new SaveMessage(SetActivity.this);
        sm.colorsave(color);
    }
    private void loadMe(){
        SaveMessage save = new SaveMessage(SetActivity.this);
        int color = save.colorload(); //将提取到的共享参数赋值给boo
        if(color!=0){
            toolbar.setBackgroundResource(color);
        }
    }
}
