package edu.feicui.newsday.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.entity.MyCollectInfo;


/**
 * Created by 太上老君 on 2016/7/21.
 */
public class MyCollectDB {
    static Context context;
    static SQLiteDatabase db;

    public MyCollectDB(Context context) {
        this.context = context;
        MyCollectDBHelper helper = new MyCollectDBHelper(context);
        db = helper.getReadableDatabase();
    }

    /**
     * 增加方法
     */
    public void Add(String title,String pic,String name,String date,String url){
        ContentValues cv = new ContentValues();
        cv.put("title",title);
        cv.put("pic",pic);
        cv.put("name",name);
        cv.put("date",date);
        cv.put("url",url);
        db.insert("collect", null, cv);//null:主键列名
    }

    /**
     * 删除一个的方法
     */
    public void delete(String title){
        db.delete("collect", "title=?", new String[]{title});
    }
    /**
     * 删除全部的方法
     */
    public void delete(){
        db.delete("collect",null,null);
    }

    /**
     * 查询所有方法
     */
    public List<MyCollectInfo> findAll(){
        List<MyCollectInfo> list = new ArrayList<MyCollectInfo>();
        Cursor cursor = null;
        cursor = db.query ("collect",null,null,null,null,null,null);
        if(cursor != null){
            while(cursor.moveToNext()){
                String title = cursor.getString(0);
                String pic = cursor.getString(1);
                String name = cursor.getString(2);
                String date = cursor.getString(3);
                String url = cursor.getString(4);
                MyCollectInfo collect = new MyCollectInfo(title, pic, name, date, url);
                list.add(collect);
            }
        }
        return list;
    }
    /**
     * 查询方法
     */
    public MyCollectInfo find(String title){
        MyCollectInfo m = null;
        Cursor cursor = db.rawQuery("select * from collect where title=?",new String[]{title});
        if(cursor!=null){
            while(cursor.moveToNext()){
                String mtitle = cursor.getString(cursor.getColumnIndex("title"));
                String mpic = cursor.getString(cursor.getColumnIndex("pic"));
                String mname = cursor.getString(cursor.getColumnIndex("name"));
                String mdate = cursor.getString(cursor.getColumnIndex("date"));
                String murl = cursor.getString(cursor.getColumnIndex("url"));
                m = new MyCollectInfo(mtitle, mpic, mname, mdate, murl);
            }
        }
        return m;
    }
}
