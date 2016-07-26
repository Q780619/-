package edu.feicui.newsday.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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

import edu.feicui.newsday.Adapter.HomehotAdapter;
import edu.feicui.newsday.R;
import edu.feicui.newsday.Utils.UrlMontage;
import edu.feicui.newsday.entity.HotInfo;
import edu.feicui.newsday.entity.HotNewsInfo;
import edu.feicui.newsday.entity.VolleySingleton;


/**
 * Created by 太上老君 on 2016/7/10.
 */
public class Home_hot extends Fragment {
    View view;
    HomehotAdapter homehotAdapter;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    List<HotNewsInfo> list_hotnews;

    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.homefragment_hot,null);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getview();
        setview();
    }
    public void getview(){
        recyclerView = (RecyclerView) view.findViewById(R.id.home_hot_rv);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.homefra_hot_rl);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.home_hot_srl);
        list_hotnews = new ArrayList<HotNewsInfo>();
    }
    public void setview(){
        getinfo();
        homehotAdapter = new HomehotAdapter(getActivity(),list_hotnews);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(homehotAdapter);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorfirst, R.color.colorsencond,
                R.color.colorthird, R.color.colorfourth, R.color.colorfifth);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        GetNews getNews = GetNews.getInstance();
//                        list_hotnews = getNews.setNewsHotList(getActivity());
                        getHotResult();
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
    }
    private void getinfo(){
        if(list_hotnews.size()!=0){
            homehotAdapter = new HomehotAdapter(getActivity(),list_hotnews);
        }else{
//            GetNews getNews = GetNews.getInstance();
//            list_hotnews = getNews.setNewsHotList(getActivity());
            getHotResult();
        }
    }

    /**
     * 执行刷新操作
     */
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    homehotAdapter = new HomehotAdapter(getActivity(),list_hotnews);
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    homehotAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(getActivity(),"刷新成功！",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };
    private List<HotNewsInfo> getHotResult(){
        StringRequest stringRequest = new StringRequest(UrlMontage.getHotNewsUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            HotInfo info = new Gson().fromJson(response,HotInfo.class);
                            List<HotInfo.RetDataEntity> contentlist = info.getRetData();
                            list_hotnews.clear();
                            for(int i = 0;i<contentlist.size();i++){
                                String title = contentlist.get(i).getTitle();
                                String abstractX = contentlist.get(i).getAbstractX();
                                String pic = contentlist.get(i).getImage_url();
                                String url = contentlist.get(i).getUrl();
                                HotNewsInfo hotNewsInfo = new HotNewsInfo(title,pic,abstractX,url);
                                list_hotnews.add(hotNewsInfo);
                            }

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("apikey", UrlMontage.NEWS_KEY);
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
        VolleySingleton.getVolleySingleton(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);
        return list_hotnews;
    }
}
