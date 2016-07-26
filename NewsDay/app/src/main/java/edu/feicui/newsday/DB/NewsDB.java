package edu.feicui.newsday.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.entity.NewsInfo;

/**
 * Created by 太上老君 on 2016/7/22.
 */
public class NewsDB {
    static Context context;
    static SQLiteDatabase db;

    public NewsDB(Context context) {
        this.context = context;
        NewsDBHelper helper = new NewsDBHelper(context);
        db = helper.getReadableDatabase();
    }

    /**
     * 增加方法
     */
    public void Add(String title,String pic,String name,String link,String desc){
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("pic",pic);
        cv.put("name",name);
        cv.put("link",link);
        cv.put("date",desc);
        db.insert("news", null, cv);//null:主键列名
    }

    /**
     * 删除一个的方法
     */
    public void delete(String title){
        db.delete("news", "title=?", new String[]{title});
    }
    /**
     * 删除全部的方法
     */
    public void delete(){
        db.delete("news",null,null);
    }

    /**
     * 查询所有方法
     */
    public List<NewsInfo> findAll(){
        List<NewsInfo> list = new ArrayList<NewsInfo>();
        Cursor cursor = null;
        cursor = db.query ("news",null,null,null,null,null,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                String title = cursor.getString(0);
                String pic = cursor.getString(1);
                String name = cursor.getString(2);
                String link = cursor.getString(3);
                String desc = cursor.getString(4);
                NewsInfo newsInfo = new NewsInfo(title, pic, name,link,desc);
                list.add(newsInfo);
            }
        }
        return list;
    }
    /**
     * 查询方法
     */
    public NewsInfo find(String title){
        NewsInfo m = null;
        Cursor cursor = db.rawQuery("select * from news where title=?",new String[]{title});
        if(cursor!=null){
            while(cursor.moveToNext()){
                String mtitle = cursor.getString(cursor.getColumnIndex("title"));
                String mpic = cursor.getString(cursor.getColumnIndex("pic"));
                String mname = cursor.getString(cursor.getColumnIndex("name"));
                String murl = cursor.getString(cursor.getColumnIndex("url"));
                String mdesc = cursor.getString(cursor.getColumnIndex("date"));

                m = new NewsInfo(mtitle, mpic, mname, murl, mdesc);
            }
        }
        return m;
    }
}
