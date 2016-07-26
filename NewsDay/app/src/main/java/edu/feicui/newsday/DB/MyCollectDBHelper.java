package edu.feicui.newsday.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 太上老君 on 2016/7/21.
 */
public class MyCollectDBHelper extends SQLiteOpenHelper {
    static final String CO_DB_NAME = "MyCollect.db";
    static final int VERSION = 1;

    public MyCollectDBHelper(Context context) {
        super(context, CO_DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table collect(title text,pic text,name text,date text,url text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
