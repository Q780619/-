package edu.feicui.newsday.entity;

import java.io.Serializable;

/**
 * Created by 太上老君 on 2016/7/21.
 */
public class MyCollectInfo implements Serializable{
    private String title;
    private String pic1;
    private String name;
    private String date;
    private String url;

    public MyCollectInfo(String title, String pic1, String name, String date, String url) {
        this.title = title;
        this.pic1 = pic1;
        this.name = name;
        this.date = date;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic1() {
        return pic1;
    }

    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
