package edu.feicui.newsday.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.feicui.newsday.Adapter.AddRecycleViewAdapter;
import edu.feicui.newsday.Adapter.CancleRecyclerViewAdapter;
import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.RecycleViewItem;
import edu.feicui.newsday.Utils.SaveMessage;


/**
 * Created by 太上老君 on 2016/7/14.
 */
public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView add_rv,cancle_rv;
    AddRecycleViewAdapter aad;
    CancleRecyclerViewAdapter cad;
    List<String> listadd,listcancle;
    Set<String> setadd,setcancle;
    List<String> liststate;
    SaveMessage saveMessage;
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
        setContentView(R.layout.add);
    }

    public void getview() {
        listadd = new ArrayList<String>();
        listcancle = new ArrayList<String>();
        liststate = new ArrayList<String>();
        setadd = new HashSet<String>();
        setcancle = new HashSet<String>();

        saveMessage = new SaveMessage(this);
        add_rv = (RecyclerView) findViewById(R.id.add_add_rv);
        cancle_rv = (RecyclerView) findViewById(R.id.add_cancel_rv);

        toolbar = (Toolbar) findViewById(R.id.add_toolbar);
    }

    public void setview() {

        toolbar.setTitle("NewsDay");
        loadMessage();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.aboutus_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getMessage();
        setadd();
        aad = new AddRecycleViewAdapter(listadd,AddActivity.this);
        add_rv.setLayoutManager(new LinearLayoutManager(this));
        add_rv.addItemDecoration(new RecycleViewItem(this, LinearLayoutManager
                .HORIZONTAL, 1, Color.GRAY));
        add_rv.setAdapter(aad);
        aad.setRecycleViewClickListener(new AddRecycleViewAdapter.RecycleViewClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                switch (listadd.get(position)) {
                    case "互联网":
                        setcancle("互联网");
                        break;
                    case "安卓":
                        setcancle("安卓");
                        break;
                    case "热点":
                        setcancle("热点");
                        break;
                    case "四川":
                        setcancle("四川");
                        break;
                    case "星座":
                        setcancle("星座");
                        break;
                    case "军事":
                        setcancle("军事");
                        break;
                }
                cad = new CancleRecyclerViewAdapter(listcancle,AddActivity.this);
                cancle_rv.setLayoutManager(new GridLayoutManager(AddActivity.this, 8));
                cad.notifyDataSetChanged();
                setMessage();
            }

            public void OnItemLongClickListener(View view, int position) {

            }
        });
        cad = new CancleRecyclerViewAdapter(listcancle,AddActivity.this);
        cancle_rv.setLayoutManager(new GridLayoutManager(this, 8));
        cancle_rv.setAdapter(cad);
        cad.setRecycleViewClickListener(new CancleRecyclerViewAdapter.RecycleViewClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                switch (listcancle.get(position)) {
                    case "互联网":
                        deletecancle("互联网");
                        break;
                    case "安卓":
                        deletecancle("安卓");
                        break;
                    case "热点":
                        deletecancle("热点");
                        break;
                    case "四川":
                        deletecancle("四川");
                        break;
                    case "星座":
                        deletecancle("星座");
                        break;
                    case "军事":
                        deletecancle("军事");
                        break;
                }
                cad = new CancleRecyclerViewAdapter(listcancle, AddActivity.this);
                cancle_rv.setLayoutManager(new GridLayoutManager(AddActivity.this, 8));
                cad.notifyDataSetChanged();
                setMessage();
            }

            @Override
            public void OnItemLongClickListener(View view, int position) {

            }
        });

    }
    @Override
    public void onClick(View v) {
    }

    /**
     * 添加recyclerview数据添加
     */
    private void setadd(){
        if(listadd.size()==0){
            Log.i("msg","执行添加标签");
            listadd.add("互联网");
            listadd.add("安卓");
            listadd.add("热点");
            listadd.add("四川");
            listadd.add("星座");
            listadd.add("军事");
            Log.i("msg", "执行添加标签" + listadd.size());
        }
    }

    /**
     * recyclerview数据添加
     */
    private void setcancle(String str){
        if(setadd!=null){
            setcancle.add(str);
            listcancle.clear();
            listcancle.addAll(setcancle);
        }else{
            Log.i("msg1","setadd==null");
        }
    }
    private void deletecancle(String str){
        setcancle.remove(str);
        listcancle.clear();
        listcancle.addAll(setcancle);
    }

    /**
     * 保存共享参数
     */
    private void setMessage(){
        saveMessage.addactivitysave(setcancle);
    }
    /**
     * 获取共享参数
     */
    private void getMessage(){
        listcancle = saveMessage.addActivityload();
        for(int i = 0;i<listcancle.size();i++){
            setcancle.add(listcancle.get(i));
        }
    }
    private void loadMessage(){
        SaveMessage save = new SaveMessage(AddActivity.this);
        int color = save.colorload(); //将提取到的共享参数赋值给boo
        if(color!=0){
            toolbar.setBackgroundResource(color);
        }
    }
}
