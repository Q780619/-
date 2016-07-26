package edu.feicui.newsday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.Adapter.MyCollectAdapter;
import edu.feicui.newsday.Base.BaseActivity;
import edu.feicui.newsday.DB.MyCollectDB;
import edu.feicui.newsday.R;
import edu.feicui.newsday.entity.MyCollectInfo;
/**
 * Created by 太上老君 on 2016/7/21.
 */
public class MyCollectActivity extends BaseActivity {
    MyCollectAdapter myCollectAdapter;
    RecyclerView recyclerView;
    List<MyCollectInfo> myCollelist;
    @Override
    public void setLayout() {
        setContentView(R.layout.mycollect);
    }

    @Override
    public void getview() {
        recyclerView = (RecyclerView)findViewById(R.id.mycollect_rv);
        myCollelist = new ArrayList<MyCollectInfo>();
    }

    @Override
    public void setview() {
        MyCollectDB myCollectDB = new MyCollectDB(MyCollectActivity.this);
        myCollelist = myCollectDB.findAll();
        if(myCollelist.size()!=0){
            myCollectAdapter = new MyCollectAdapter(MyCollectActivity.this,myCollelist);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.setAdapter(myCollectAdapter);
            myCollectAdapter.setRecycleViewClickListener(new MyCollectAdapter.RecycleViewClickListener() {
                @Override
                public void OnItemClickListener(View view, int position) {
                    MyCollectInfo myCollectInfo = myCollelist.get(position);
                    Intent intent = new Intent(MyCollectActivity.this, NewsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("skip", myCollectInfo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                @Override
                public void OnItemLongClickListener(View view, int position) {

                }
            });
        }else{
            Toast.makeText(MyCollectActivity.this,"还没有数据，请加载数据！",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View v) {

    }
}
