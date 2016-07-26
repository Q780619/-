package edu.feicui.newsday.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 太上老君 on 2016/7/10.
 */
public class SaveMessage {
    Context context;
    public SaveMessage(Context context) {
        this.context = context;
    }
    /**
     * 向共享参数文件中添加简单数据
     * @param boo 传入的对象
     */
    public void statussave(String boo){
        SharedPreferences spf = context.
                getSharedPreferences("Open", Context.MODE_PRIVATE);//产生一个共享参数文件:Open:文件名
        SharedPreferences.Editor editor = spf.edit();//获得共享参数的操作体
        editor.putString("status", boo);//存入数据
        editor.commit();//提交数据
    }
    public void addactivitysave(Set<String> set){
        SharedPreferences spf = context.getSharedPreferences("Add", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();//获得共享参数的操作体
        editor.putStringSet("isadd", set);//存入数据
        editor.commit();//提交
    }
    public void homeInfoAddSave(String boo){
        SharedPreferences spf = context.getSharedPreferences("IsExist", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = spf.edit();
        editor.putString("isexist", boo);
        editor.commit();
    }
    public void tzsave(String boo){
        SharedPreferences spf = context.
                getSharedPreferences("Tz", Context.MODE_PRIVATE);//产生一个共享参数文件
        SharedPreferences.Editor editor = spf.edit();//获得共享参数的操作体
        editor.putString("tz",boo);
        editor.commit();//提交
    }
    public void colorsave(int color){
        SharedPreferences spf = context.
                getSharedPreferences("Color", Context.MODE_PRIVATE);//产生一个共享参数文件
        SharedPreferences.Editor editor = spf.edit();//获得共享参数的操作体
        editor.putInt("co", color);
        editor.commit();//提交
    }




    /**
     * 从共享参数文件中拿东西
     * @return 返回String类型 boo
     */
    public String statusload(){
        SharedPreferences spf = context.getSharedPreferences("Open", Context.MODE_PRIVATE);
        String boo = spf.getString("status", "true");//true:初始值：true
        return boo;
    }

    /**
     * 拿数据
     * @return
     */
    public List<String> addActivityload(){  //load:加载
        List<String> list = new ArrayList<String>();
        SharedPreferences spf = context.getSharedPreferences("Add", Context.MODE_PRIVATE);
        Set<String> set = spf.getStringSet("isadd", null);//拿数据。null:初始值,可为空
        if(set==null){
            set = new HashSet<String>();
        }
        list.addAll(set);
        return list;
    }
    public String homeInfoAddLoad(){
        SharedPreferences spf =context.getSharedPreferences("IsExist", Context.MODE_PRIVATE);
        String boo = spf.getString("isexist","none");
        return boo;
    }
    public String tzload(){
        SharedPreferences spf = context.getSharedPreferences("Tz", Context.MODE_PRIVATE);
        String boo = spf.getString("tz","none");//none:初始值：none
        return boo;
    }
    public int colorload(){
        SharedPreferences spf = context.getSharedPreferences("Color", Context.MODE_PRIVATE);
        int color = spf.getInt("co",0);
        return color;
    }
}
