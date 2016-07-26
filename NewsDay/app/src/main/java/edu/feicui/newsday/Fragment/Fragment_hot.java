package edu.feicui.newsday.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.feicui.newsday.Activity.NewsActivity;
import edu.feicui.newsday.Adapter.Fragment_hotAdapter;
import edu.feicui.newsday.DB.NewsDB;
import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.SaveMessage;
import edu.feicui.newsday.Utils.Tools;
import edu.feicui.newsday.Utils.UrlMontage;
import edu.feicui.newsday.entity.NewsInfo;
import edu.feicui.newsday.entity.VolleySingleton;


/**
 * Created by 太上老君 on 2016/7/10.
 */
public class Fragment_hot extends Fragment {
    View view;
    Fragment_hotAdapter fragment_hotAdapter;
    RecyclerView recyclerView;
    List<BaiDuiInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean> bdlist;
    List<NewsInfo> list_news;
    SaveMessage saveMessage;
    LinearLayoutManager layoutManager;
    String pic = null;

    private String tab = null;
    SwipeRefreshLayout swipeRefreshLayout;

    boolean isfinish = false;
    boolean isgetallnews = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tab = getArguments().getString("newType");
        view = inflater.inflate(R.layout.fragment,container,false);
        return view;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    @Override
    public void onResume() {
        super.onResume();
        getview();
        setview();
    }

    private void getview(){
        recyclerView = (RecyclerView) view.findViewById(R.id.fra_hot_rv);
        saveMessage = new SaveMessage(getActivity());
        layoutManager = new LinearLayoutManager(getActivity());
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.fra_hot_srl);
    }
    private void setview(){
        setlist(Tools.page);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorfirst, R.color.colorsencond,
                R.color.colorthird, R.color.colorfourth, R.color.colorfifth);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        setlist(Tools.page);
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int layoutitem = layoutManager.findLastVisibleItemPosition();
                super.onScrollStateChanged(recyclerView, newState);
                Log.i("qsq", layoutitem + "开启"+(fragment_hotAdapter.getItemCount()-1));
                if(layoutitem==fragment_hotAdapter.getItemCount()-1){
                    switch (newState){
                        case 0:
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    if(Tools.page<=Tools.allpage){
                                        setlist(Tools.page);
                                        Log.i("msg", "page:" + Tools.page + "Allpage:" + Tools.allpage);
                                        handler.sendEmptyMessage(3);
                                    }else{
                                        isfinish = true;
                                    }
                                }
                            }).start();
                            break;
                        case 1:
                            break;
                        case 2:
                            Tools.page++;
                            Log.i("msg", "page:" + Tools.page);
                            Toast.makeText(getActivity(),"加载中",Toast.LENGTH_SHORT).show();
                            if(isfinish){
                                Toast.makeText(getActivity(),"到底了",Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                }
            }
        });
    }
    private void setlists(){
        NewsInfo newsInfo;
        list_news = new ArrayList<NewsInfo>();
        if(bdlist!=null&&bdlist.size()!=0){
            if(bdlist.size()==0){
                Log.i("msg","qqqqqqqqqqqwwwwwwwwwweeeeeeeeee");
            }
            NewsDB newsDB = new NewsDB(getActivity());
            int num = newsDB.findAll().size();
            for(int i = 0;i<bdlist.size();i++){
                String title = bdlist.get(i).getTitle();
                String id = bdlist.get(i).getChannelId();
                String name = bdlist.get(i).getChannelName();
                String desc = bdlist.get(i).getDesc();
                List<BaiDuiInfo.ShowapiResBodyBean.PagebeanBean.ContentlistBean.ImageEntity>
                        imageEntityList = bdlist.get(i).getImageurls();
                String link = bdlist.get(i).getLink();
                if(imageEntityList!=null&&imageEntityList.size()!=0){
                    pic = imageEntityList.get(0).getUrl();
                }else{
                    pic = "";
                }
                Log.i("msg", "我走了这里" + title + "  " + pic + "  " + name + "  " + link + " " + desc);
                if(i==0&&(num!=0)){
                    newsDB.delete();
                }
                newsDB.Add(title,pic,name,link,desc);
//                list_news = newsDB.findAll();
                newsInfo = new NewsInfo(title,pic,name,link,desc);
                list_news.add(newsInfo);
            }
            Log.i("msg","大小："+newsDB.findAll().size());
        }
    }
    private void setlist(int page){
        String url = UrlMontage.getNewsUrl(tab,page);//拿到新闻地址
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            BaiDuiInfo info =new Gson().fromJson(response,BaiDuiInfo.class);

                            if(!isgetallnews){
                                Tools.allpage = info.getShowapi_res_body().getPagebean().getAllPages();
                                isgetallnews = true;
                            }
                            if(bdlist==null){
                                bdlist = new ArrayList<>();
                            }
                            if(bdlist.size()!=0){
                                bdlist.addAll(info.getShowapi_res_body().getPagebean().getContentlist());
                            }else{
                                bdlist = info.getShowapi_res_body().getPagebean().getContentlist();
                            }
                            Log.i("sss",Tools.allpage+"总页数：");
                            handler.sendEmptyMessage(1);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("msg","错误");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("apikey", UrlMontage.NEWS_KEY);
//                Log.i("msg","拼接");
                return headers;
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String str = null;
                try {
                    str = new String(response.data, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        //将StringRequest对象添加进RequestQueue请求队列中
        VolleySingleton.getVolleySingleton(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
    }

    Handler handler = new Handler(){
    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        swipeRefreshLayout.setRefreshing(false);
        switch (msg.what){
            case 0:
                if(list_news==null){
                    list_news = new ArrayList<>();
                }
                fragment_hotAdapter = new Fragment_hotAdapter(getActivity(),list_news);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(fragment_hotAdapter);
                recyclerView.setLayoutManager(layoutManager);
                    break;
                case 1:
                    if(bdlist==null){
                        bdlist = new ArrayList<>();
                    }
                case 3:
                    setlists();
                    fragment_hotAdapter = new Fragment_hotAdapter(getActivity(),list_news);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(fragment_hotAdapter);
                    recyclerView.setLayoutManager(layoutManager);
                    break;
            }
            fragment_hotAdapter.setRecycleViewClickListener(new Fragment_hotAdapter.RecycleViewClickListener() {
                @Override
                public void OnItemClickListener(View view, int position) {
                    NewsInfo newsInfo = list_news.get(position);
                    Intent intent = new Intent(getActivity(), NewsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("select", newsInfo);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

                @Override
                public void OnItemLongClickListener(View view, int position) {

                }
            });
        }
    };
}
