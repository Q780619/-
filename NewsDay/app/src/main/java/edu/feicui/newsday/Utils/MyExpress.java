package edu.feicui.newsday.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.feicui.newsday.DB.MyCollectDBHelper;
import edu.feicui.newsday.entity.MyCollectInfo;

/**
 * Created by 太上老君 on 2016/7/24.
 */
public class MyExpress {
    Context context;
    SQLiteDatabase db;

    public MyExpress(Context context) {
        this.context = context;
        MyCollectDBHelper cdh=new MyCollectDBHelper(context);
        db=cdh.getReadableDatabase();
    }

    public List<MyCollectInfo> getData(String in){
        List<MyCollectInfo> list=new ArrayList<MyCollectInfo>();
        //select  * from 表名 where 列名 like ？ ,new String []{'%'+传递进来查询的值+'%'};注意用rawQuery方法只能用单引号
        Cursor cursor=db.rawQuery("select * from collect where title like ?",new String[]{'%'+in+'%'});//Like代表通配符
        if(cursor!=null){
            while(cursor.moveToNext()){
                String title=cursor.getString(cursor.getColumnIndex("title"));
                String pic=cursor.getString(cursor.getColumnIndex("pic"));
                String name=cursor.getString(cursor.getColumnIndex("name"));
                String date=cursor.getString(cursor.getColumnIndex("date"));
                String url=cursor.getString(cursor.getColumnIndex("url"));
                MyCollectInfo stu=new MyCollectInfo(title,pic,name,date,url);
                list.add(stu);
            }
        }
        return list;
    }
}
