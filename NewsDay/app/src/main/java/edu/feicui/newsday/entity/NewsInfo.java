package edu.feicui.newsday.entity;


import java.io.Serializable;

/**
 * Created by 太上老君 on 2016/7/15.
 */
public class NewsInfo implements Serializable{
    private String title;
    private String pic;
    private String name;
    private String url;
    private String desc;

    public NewsInfo(String title, String pic, String name, String url, String desc) {
        this.title = title;
        this.pic = pic;
        this.name = name;
        this.url = url;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
