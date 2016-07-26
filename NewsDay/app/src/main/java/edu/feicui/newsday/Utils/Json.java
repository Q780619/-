package edu.feicui.newsday.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.entity.HotNewsInfo;
import edu.feicui.newsday.entity.NewsInfo;


/**
 * Created by 太上老君 on 2016/7/12.
 */
public class Json {
    static String title,url;
    static String abs,image_url;
    static List<NewsInfo> list = new ArrayList<>();
    static List<HotNewsInfo> hotlist = new ArrayList<>();
    static NewsInfo newsInfo;
    static HotNewsInfo hotNewsInfo;
    public static List<NewsInfo> Json(String str){
        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(str);
            int errNum = jsonObject.getInt("errNum");
            String errMsg = jsonObject.getString("errMsg");
            JSONArray retData = jsonObject.getJSONArray("retData");
            for(int i = 0;i<retData.length();i++){
                JSONObject data_jo = retData.getJSONObject(i);
                title = data_jo.getString("title");
                url = data_jo.getString("url");//新闻链接
                abs = data_jo.getString("abstract");//新闻简介
                image_url = data_jo.getString("image_url");//图片
                hotNewsInfo = new HotNewsInfo(title,image_url,abs,url);
                hotlist.add(hotNewsInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//                Log.i("msg", "Json解析后的hotlist长度为" + list.size());
        return list;
    }
    public static List<HotNewsInfo> hotJson(String str){
        try {
            JSONObject jsonObject;
            jsonObject = new JSONObject(str);
            int errNum = jsonObject.getInt("errNum");
            String errMsg = jsonObject.getString("errMsg");
            JSONArray retData = jsonObject.getJSONArray("retData");
            for(int i = 0;i<retData.length();i++){
                JSONObject data_jo = retData.getJSONObject(i);
                title = data_jo.getString("title");
                url = data_jo.getString("url");//新闻链接
                abs = data_jo.getString("abstract");//新闻简介
                image_url = data_jo.getString("image_url");//图片
                hotNewsInfo = new HotNewsInfo(title,image_url,abs,url);
                hotlist.add(hotNewsInfo);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return hotlist;
    }

}
