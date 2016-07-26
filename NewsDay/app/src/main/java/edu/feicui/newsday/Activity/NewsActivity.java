package edu.feicui.newsday.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import edu.feicui.newsday.Base.BaseActivity;
import edu.feicui.newsday.DB.MyCollectDB;
import edu.feicui.newsday.Fragment.BaiDuiInfo;
import edu.feicui.newsday.R;
import edu.feicui.newsday.entity.MyCollectInfo;
import edu.feicui.newsday.entity.NewsInfo;


/**
 * Created by 太上老君 on 2016/7/20.
 */
public class NewsActivity extends BaseActivity {
    WebView webView;
    String title,pic,name,desc,url;
    ProgressBar pb;
    ImageView imageView;
    List<NewsInfo> list;
    @Override
    public void setLayout() {
        setContentView(R.layout.news);
    }

    @Override
    public void getview() {
        pb = (ProgressBar) findViewById(R.id.news_pb);
        webView = (WebView) findViewById(R.id.news_wv);
        imageView = (ImageView) findViewById(R.id.news_collect_iv);
    }

    @Override
    public void setview() {
        Bundle bundle = getIntent().getExtras();
        NewsInfo newsInfo = (NewsInfo) bundle.getSerializable("select");
        MyCollectInfo myCollectInfo = (MyCollectInfo) bundle.getSerializable("skip");
        if(newsInfo!=null){
            title = newsInfo.getTitle();
            pic = newsInfo.getPic();
            desc = newsInfo.getDesc();
            url = newsInfo.getUrl();
            name = newsInfo.getName();
        }
        if(myCollectInfo!=null){
            title = myCollectInfo.getTitle();
            pic = myCollectInfo.getPic1();
            name = myCollectInfo.getName();
            desc = myCollectInfo.getDate();
            url = myCollectInfo.getUrl();
        }
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    pb.setVisibility(View.GONE);
                } else {
                    pb.setProgress(newProgress);
                }
            }
        });
        imageView.setOnClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_BACK:
                webView.goBack();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.news_collect_iv:
                MyCollectDB myCollectDB = new MyCollectDB(NewsActivity.this);
                if(myCollectDB.find(title)==null){
                    Toast.makeText(NewsActivity.this,"执行添加！",Toast.LENGTH_SHORT).show();
                    myCollectDB.Add(title,pic,name,desc,url);
                }else{
                    Toast.makeText(NewsActivity.this,"该数据已存在！",Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }


}
