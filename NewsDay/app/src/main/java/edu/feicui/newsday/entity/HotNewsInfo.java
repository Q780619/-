package edu.feicui.newsday.entity;

/**
 * Created by 太上老君 on 2016/7/19.
 */
public class HotNewsInfo {
    private String title;
    private String pic1;
    private String abs;
    private String url;

    public HotNewsInfo(String title, String pic1, String abs, String url) {
        this.title = title;
        this.pic1 = pic1;
        this.abs = abs;
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

    public String getAbs() {
        return abs;
    }

    public void setAbs(String abs) {
        this.abs = abs;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
