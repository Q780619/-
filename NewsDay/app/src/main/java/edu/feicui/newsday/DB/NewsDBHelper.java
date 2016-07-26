package edu.feicui.newsday.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 太上老君 on 2016/7/22.
 */
public class NewsDBHelper extends SQLiteOpenHelper{
    static final String NEWS_DB_NAME = "News.db";
    static final int VERSION = 1;

    public NewsDBHelper(Context context) {
        super(context, NEWS_DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table news(title text,pic text,name text,link text,desc text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
