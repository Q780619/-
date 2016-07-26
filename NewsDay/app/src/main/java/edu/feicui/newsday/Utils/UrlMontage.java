package edu.feicui.newsday.Utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 太上老君 on 2016/7/19.
 */
public class UrlMontage {
    public static final String NEWS_URL="http://apis.baidu.com/showapi_open_bus/channel_news/search_news";
    public static final String NEWS_KEY="c895b7e48596ee645868994a1095d88e";
    public static final String NEWS_HOT_URL="http://apis.baidu.com/songshuxiansheng/news/news";
    //拼接地址
    public static String getNewsUrl(String title,int page){
        String path=NEWS_URL+"?title="+ChangeEncode(title)+"&page="+page;
        return path;
    }
    public static String getHotNewsUrl(){
        return  NEWS_HOT_URL;

    }
    //修改编码
    public static String ChangeEncode(String titlename){
        try {
            titlename= URLEncoder.encode(titlename, "utf-8");
        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
            titlename="";
        }
        return titlename;

    }
}
